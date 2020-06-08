package com.kdj.mlink.ezup3.shop.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.openqa.selenium.chrome.ChromeDriver;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kdj.mlink.ezup3.shop.common.ChromeExtention;
import com.kdj.mlink.ezup3.shop.common.IShopOrderCommand;
import com.kdj.mlink.ezup3.shop.common.IShopQuestion;
import com.kdj.mlink.ezup3.shop.common.OrderStatus;
import com.kdj.mlink.ezup3.shop.common.ProductFactory;
import com.kdj.mlink.ezup3.shop.common.ProductQuestionService;
import com.kdj.mlink.ezup3.shop.common.ProductService;
import com.kdj.mlink.ezup3.shop.common.QuestionStatus;
import com.kdj.mlink.ezup3.shop.common.SchedulerService;
import com.kdj.mlink.ezup3.shop.common.ShopCommon;
import com.kdj.mlink.ezup3.shop.common.ShopOrderContext;
import com.kdj.mlink.ezup3.shop.dao.ScheduleInfoDto;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderDao;
import com.kdj.mlink.ezup3.shop.dao.ShopOrderMstDto;
import com.kdj.mlink.ezup3.shop.dao.ShopProductDto;
import com.kdj.mlink.ezup3.shop.dao.ShopQuestionDao;
import com.kdj.mlink.ezup3.common.YDMAProgressBar;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.AlimTalkChargeDao;
import com.kdj.mlink.ezup3.data.dao.CompInfoDao;
import com.kdj.mlink.ezup3.data.dao.QuestListDto;

public class ShopOrderJob extends IShopOrderJob {
	static final Logger log = LoggerFactory.getLogger(ShopOrderJob.class);
	ChromeExtention chrome = ChromeExtention.getInstace();
	ChromeDriver driver = null;

	@Override
	protected int setConfirm(JobExecutionContext context, ScheduleInfoDto value, List<ShopOrderMstDto> list) {
		try {
			List<ShopOrderMstDto> datasource = new ArrayList<>();
			datasource = ShopOrderDao.get().getShopOrderList(YDMATimeUtil.getCurrentDateHanjin().concat("0000"),
					YDMATimeUtil.getCurrentDateHanjin().concat("2359"), "", "부분일치", "", "REG_DATE");
			List<ShopOrderMstDto> resultList = new ArrayList<ShopOrderMstDto>();
			String jobexe21 = value.getJobexe11() == null ? "" : value.getJobexe11();
			String jobexe22 = value.getJobexe12() == null ? "" : value.getJobexe12();
			String jobexe23 = value.getJobexe13() == null ? "" : value.getJobexe13();
			String jobexe24 = value.getJobexe14() == null ? "" : value.getJobexe14();
			Map<String, List<ShopOrderMstDto>> mapList = datasource.stream().collect(Collectors.toList()).stream()
					.collect(Collectors.groupingBy(ShopOrderMstDto::getShopid));
			for (ShopOrderMstDto orgshopcd : list) {
				for (String shopcd : mapList.keySet()) {
					if (shopcd.equals(orgshopcd.getShopid())) {
						List<ShopOrderMstDto> listdto = mapList.get(shopcd);
						if (jobexe21.equals("Y") | jobexe22.equals("Y") | jobexe23.equals("Y") | jobexe24.equals("Y")) {
							if (value.getOutofexe() != null) {
								String[] getdate = value.getOutofexe().split(",");
								for (String date : getdate) {
									if (!date.equals(YDMATimeUtil.getCurrentDateScheduler())) {
										if (listdto != null && listdto.size() > 0) {
											for(ShopOrderMstDto dto :listdto) {
												dto.setStartDt(YDMATimeUtil.getCurrentDateHanjin());
												dto.setEndDt(YDMATimeUtil.getCurrentDateHanjin());
											}
											// ---- 신규 주문건만 가져온다.
											listdto = listdto.stream()
													.filter(p -> p.getOrder_status().equals(OrderStatus.신규주문))
													.collect(Collectors.toList());

											// --- 신규 주문건이 있으면 주문 호가인 한다.
											if (listdto != null && listdto.size() > 0) {
												System.out.println(
														"주문확인 시작 : " + YDMATimeUtil.getCurrentTimeByNewFormat());
												resultList = ShopOrderContext.get()
														.setContext(
																IShopOrderCommand.getContext(shopcd, OrderStatus.주문확인))
														.excute(listdto);
												System.out.println(
														"주문확인 종료 : " + YDMATimeUtil.getCurrentTimeByNewFormat());
											}
											if (resultList.size() > 0) {
												ShopOrderDao.get().SaveOrUpdate(resultList); // 신규 주문건 디비에 저장한다.

											}

										}
									}
								}
							}
						} else {
							return 0;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	protected int getNewOrder(JobExecutionContext context, ScheduleInfoDto value, List<ShopOrderMstDto> list) {
		List<ShopOrderMstDto> resultList = new ArrayList<ShopOrderMstDto>();
		try {
			String jobexe21 = value.getJobexe21() == null ? "" : value.getJobexe21();
			String jobexe22 = value.getJobexe22() == null ? "" : value.getJobexe22();
			String jobexe23 = value.getJobexe23() == null ? "" : value.getJobexe23();
			String jobexe24 = value.getJobexe24() == null ? "" : value.getJobexe24();
			if (jobexe21.equals("Y") | jobexe22.equals("Y") | jobexe23.equals("Y") | jobexe24.equals("Y")) {
				if (value.getOutofexe() != null) {
					String[] getdate = value.getOutofexe().split(",");
					for (String date : getdate) {
						if (!date.equals(YDMATimeUtil.getCurrentDateScheduler())) {
							for (ShopOrderMstDto dto : list) {
								if (dto.getShopid().equals(ShopCommon.쿠팡)) {
									dto.setVendorId(dto.getApikey());
								}

								List<ShopOrderMstDto> args = Arrays.asList(dto); // 해당 주문 건을 보낼 파라미터를 만든다.
								System.out.println("신규주문수집 : " + YDMATimeUtil.getCurrentTimeByNewFormat());
								resultList.addAll(ShopOrderContext.get()
										.setContext(IShopOrderCommand.getContext(dto.getShopid(), OrderStatus.신규주문))
										.excute(args).stream()
										.filter(d -> d.getResult_code() != null
												&& d.getResult_code().equals(ShopCommon.정상처리))
										.collect(Collectors.toList()));
								System.out.println("신규주문종료 : " + YDMATimeUtil.getCurrentTimeByNewFormat());
								if (resultList.size() > 0) {
									ShopOrderDao.get().SaveOrUpdate(resultList); // 신규 주문건 디비에 저장한다.

								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	protected int getOrderSync(JobExecutionContext context, ScheduleInfoDto value, List<ShopOrderMstDto> list) {
		try {
			List<ShopOrderMstDto> datasource = new ArrayList<>();
			datasource = ShopOrderDao.get().getShopOrderList(YDMATimeUtil.getCurrentDateHanjin().concat("0000"),
					YDMATimeUtil.getCurrentDateHanjin().concat("2359"), "", "부분일치", "", "REG_DATE");
			List<ShopOrderMstDto> resultList = new ArrayList<ShopOrderMstDto>();

			String jobexe31 = value.getJobexe31() == null ? "" : value.getJobexe31();
			String jobexe32 = value.getJobexe32() == null ? "" : value.getJobexe32();
			String jobexe33 = value.getJobexe33() == null ? "" : value.getJobexe33();
			String jobexe34 = value.getJobexe34() == null ? "" : value.getJobexe34();
			Map<String, List<ShopOrderMstDto>> mapList = datasource.stream().collect(Collectors.toList()).stream()
					.collect(Collectors.groupingBy(ShopOrderMstDto::getShopid));
			for (ShopOrderMstDto orgshopcd : list) {
				for (String shopcd : mapList.keySet()) {
					if (shopcd.equals(orgshopcd.getShopid())) {
						List<ShopOrderMstDto> listdto = mapList.get(shopcd);
						for (ShopOrderMstDto dto : listdto) {
							if (dto.getShopid().equals(ShopCommon.십일번가)) {
								dto.setStartDt(YDMATimeUtil.getCurrentDateHanjin().concat("0000"));
								dto.setEndDt(YDMATimeUtil.getCurrentDateHanjin().concat("2359"));
							} else {
								dto.setStartDt(YDMATimeUtil.getCurrentDateHanjin());
								dto.setEndDt(YDMATimeUtil.getCurrentDateHanjin());
							}

						}
						if (jobexe31.equals("Y") | jobexe32.equals("Y") | jobexe33.equals("Y") | jobexe34.equals("Y")) {
							if (value.getOutofexe() != null) {
								String[] getdate = value.getOutofexe().split(",");
								for (String date : getdate) {
									if (!date.equals(YDMATimeUtil.getCurrentDateScheduler())) {
										// if (listdto != null && listdto.size() > 0) {
										System.out.println("씽크 : " +shopcd+ YDMATimeUtil.getCurrentTimeByNewFormat());
										resultList = ShopOrderContext.get()
												.setContext(IShopOrderCommand.getContext(shopcd, OrderStatus.조회))
												.excute(listdto).stream()
												.filter(d -> d.getResult_code() != null
														&& d.getResult_code().equals(ShopCommon.정상처리))
												.collect(Collectors.toList());
										System.out.println("씽크 종료 : " +shopcd+ YDMATimeUtil.getCurrentTimeByNewFormat());
										if (resultList.size() > 0) {
											ShopOrderDao.get().SaveOrUpdate(resultList);
										}
										// }
									}
								}
							}
						} else {
							return 0;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	protected int setOrderExpress(JobExecutionContext context, ScheduleInfoDto value, List<ShopOrderMstDto> list) {
		try {
			List<ShopOrderMstDto> datasource = new ArrayList<>();
			datasource = ShopOrderDao.get().getShopOrderList(YDMATimeUtil.getCurrentDateHanjin().concat("0000"),
					YDMATimeUtil.getCurrentDateHanjin().concat("2359"), "", "부분일치", "", "REG_DATE");
			List<ShopOrderMstDto> resultList = new ArrayList<ShopOrderMstDto>();
			String jobexe21 = value.getJobexe41() == null ? "" : value.getJobexe41();
			String jobexe22 = value.getJobexe42() == null ? "" : value.getJobexe42();
			String jobexe23 = value.getJobexe43() == null ? "" : value.getJobexe43();
			String jobexe24 = value.getJobexe44() == null ? "" : value.getJobexe44();

			Map<String, List<ShopOrderMstDto>> mapList = datasource.stream().collect(Collectors.toList()).stream()
					.collect(Collectors.groupingBy(ShopOrderMstDto::getShopid));
			for (String shopcd : mapList.keySet()) {
				List<ShopOrderMstDto> listdto = mapList.get(shopcd);
				if (jobexe21.equals("Y") | jobexe22.equals("Y") | jobexe23.equals("Y") | jobexe24.equals("Y")) {
					if (value.getOutofexe() != null) {
						String[] getdate = value.getOutofexe().split(",");
						for (String date : getdate) {
							if (!date.equals(YDMATimeUtil.getCurrentDateScheduler())) {

//								System.out.println("송장전송돈다");
//								if (datasource.size() > 0) {
//										// 주문확인..
//										resultList.add(dto);
//										// ---- 신규 주문건만 가져온다.
//										resultList = resultList.stream()
//												.filter(p -> p.getOrder_status().equals(OrderStatus.송장출력))
//												.collect(Collectors.toList());
//
//										// --- 신규 주문건이 있으면 주문 호가인 한다.
//										if (resultList != null && resultList.size() > 0) {
//											List<ShopOrderMstDto> dlvlist = new ArrayList<>();
//											ShopOrderDao dao = new ShopOrderDao();
//											for (ShopOrderMstDto a : resultList) {
//												ShopOrderMstDto dlvdto = dao.getShopOrderExpressList(a.getOrdseq());
//												dlvlist.add(dlvdto);
//											}
//											Map<String, List<ShopOrderMstDto>> mapList = dlvlist.stream()
//													.collect(Collectors.groupingBy(ShopOrderMstDto::getDlvid));
//											System.out.println(mapList);
//											for (String dlvid : mapList.keySet()) {
//												List<ShopOrderMstDto> map = mapList.get(dlvid);
//												List<ShopOrderMstDto> sendResult = new ArrayList<>();
//												if (dlvid.equals("002")) {
//													List<String> addr = dao.getNaverAddress(map, null);
//													sendResult = dao.sendPickupExpressCrawl(map, null, addr);
//												}
//											}
//										}
//
//									
//								}
							}
						}
					}
				} else {
					return 0;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;

	}

	// 메일보내기 및 카카오보내기
	@Override
	protected int sendEmailNKakao(JobExecutionContext context, ScheduleInfoDto value, List<ShopOrderMstDto> list) {
		System.out.println("메일시작");
		CompInfoDao dao = new CompInfoDao();
		try {
			AlimTalkChargeDao aldao = new AlimTalkChargeDao();
			int kakaoseq = 1;
			List<String> result = new ArrayList<>();
			List<String> complist = dao.getCompNoImage();
			List<String> charge = aldao.getAlimTalkChargeList(complist.get(0));
			List<String> allist = aldao.getAlimTalkChargeList(complist.get(0));
			String email = value.getEmailchk() == null ? "" : value.getEmailchk();
			String kakao = value.getSmschk() == null ? "" : value.getSmschk();
			if (email.equals("Y")) {
				String emailaddr = value.getEmailaddr();
				SchedulerService.get().sendEmail(complist, emailaddr);
			}
			if (kakao.equals("Y")) {
				String smsno = value.getSmsno();
				String startdt = "";
				String enddt = "";
				Date end = null;
				;
				Date start = null;
				Date now = null;
				String smschk = value.getDontchk() == null ? "" : value.getDontchk();

				if (smschk.equals("Y")) {
					startdt = value.getDontstr();
					enddt = value.getDontend();
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					start = format.parse(startdt);
					end = format.parse(enddt);
					String now1 = YDMATimeUtil.getCurrentTimeOnlyKaKao();
					now = format.parse(now1);
					if (start.getTime() < now.getTime() && end.getTime() > now.getTime()) {
						return 0;
					} else {
						result = SchedulerService.get().sendKakao(smsno, startdt, enddt, smschk, complist);
						int seq = Integer.parseInt(charge.get(7));
						String num = String.format("%011d", seq + 1);
						aldao.updateMoneyKakaoInfo(charge.get(0),
								Integer.parseInt(charge.get(4)) + Integer.parseInt(allist.get(6)),
								Integer.parseInt(charge.get(5)) - Integer.parseInt(allist.get(6)), num);
						List<String> detcharge = aldao.getAlimTalkChargeList(complist.get(0));
						String sentdate = splitMark(result.get(7)).substring(0, 8);
						String timespent = result.get(7).substring(11, result.get(7).length());
						aldao.insertMoneyKakaoDetail(detcharge.get(1) + detcharge.get(7), result.get(5), result.get(6),
								sentdate, timespent, "KAKAO");
					}
				} else {
					result = SchedulerService.get().sendKakao(smsno, startdt, enddt, smschk, complist);
					int seq = Integer.parseInt(charge.get(7));
					String num = String.format("%011d", seq + 1);
					aldao.updateMoneyKakaoInfo(charge.get(0),
							Integer.parseInt(charge.get(4)) + Integer.parseInt(allist.get(6)),
							Integer.parseInt(charge.get(5)) - Integer.parseInt(allist.get(6)), num);
					List<String> detcharge = aldao.getAlimTalkChargeList(complist.get(0));
					String sentdate = splitMark(result.get(7)).substring(0, 8);
					String timespent = result.get(7).substring(11, result.get(7).length());
					aldao.insertMoneyKakaoDetail(detcharge.get(1) + detcharge.get(7), result.get(5), result.get(6),
							sentdate, timespent, "KAKAO");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public String splitMark(String text) {
		String[] split = text.split("-| ");
		String complite = "";
		for (int j = 0; j < split.length; j++) {
			complite += split[j];
			complite = complite.trim();
		}
		return complite;
	}

	// 문의수집
	@Override
	protected int getQuestion(JobExecutionContext context, ScheduleInfoDto value, List<QuestListDto> list) {
		try {
			System.out.println("문의수집");
			System.out.println("SHOP : " + list.get(0).getShopnm());
			System.out.println("ID : " + list.get(0).getShopid());
			System.out.println("PASS : " + list.get(0).getShopPw());
			String jobexe21 = value.getJobexe11() == null ? "" : value.getJobexe11();
			String jobexe22 = value.getJobexe12() == null ? "" : value.getJobexe12();
			String jobexe23 = value.getJobexe13() == null ? "" : value.getJobexe13();
			String jobexe24 = value.getJobexe14() == null ? "" : value.getJobexe14();
			if (jobexe21.equals("Y") | jobexe22.equals("Y") | jobexe23.equals("Y") | jobexe24.equals("Y")) {
				if (value.getOutofexe() != null) {
					String[] getdate = value.getOutofexe().split(",");
					for (String date : getdate) {
						if (!date.equals(YDMATimeUtil.getCurrentDateScheduler())) {
							System.out.println("돌아가고 있는지" + YDMATimeUtil.getCurrentTimeByNewFormat());
							for (QuestListDto dto : list) {
								List<QuestListDto> searchlist = new ArrayList<>();
								List<QuestListDto> args = Arrays.asList(dto);

								ProductQuestionService service = new ProductQuestionService(
										IShopQuestion.getSearchBean(dto.getShopnm()));
								searchlist.addAll(service.excute(args));

								if (searchlist.size() > 0) {
									ShopQuestionDao.get().SaveOrUpdate(searchlist);
								}

							}
							System.out.println("문의수집 끝 ");
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	// 긴급메세지수집
	@Override
	protected int getEmergency(JobExecutionContext context, ScheduleInfoDto value, List<QuestListDto> list) {
		try {
			String jobexe21 = value.getJobexe21() == null ? "" : value.getJobexe21();
			String jobexe22 = value.getJobexe22() == null ? "" : value.getJobexe22();
			String jobexe23 = value.getJobexe23() == null ? "" : value.getJobexe23();
			String jobexe24 = value.getJobexe24() == null ? "" : value.getJobexe24();
			if (jobexe21.equals("Y") | jobexe22.equals("Y") | jobexe23.equals("Y") | jobexe24.equals("Y")) {
				if (value.getOutofexe() != null) {
					String[] getdate = value.getOutofexe().split(",");
					for (String date : getdate) {
						if (!date.equals(YDMATimeUtil.getCurrentDateScheduler())) {
							System.out.println("돌아가고 있는지" + YDMATimeUtil.getCurrentTimeByNewFormat());
							for (QuestListDto dto : list) {
								List<QuestListDto> searchlist = new ArrayList<>();
								IShopOrderCommand ctx = null;
								List<QuestListDto> args = Arrays.asList(dto);

								ProductQuestionService service = new ProductQuestionService(
										IShopQuestion.getSearchBean(dto.getShopnm()));
								searchlist.addAll(service.excute(args));

								if (searchlist.size() > 0) {
									ShopQuestionDao.get().SaveOrUpdate(searchlist);
								}

							}
							System.out.println("문의수집 끝 ");
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 상품명수집
	@Override
	protected int getProductName(JobExecutionContext context, ScheduleInfoDto value, List<QuestListDto> list) {
		try {
			String jobexe21 = value.getJobexe31() == null ? "" : value.getJobexe31();
			String jobexe22 = value.getJobexe32() == null ? "" : value.getJobexe32();
			String jobexe23 = value.getJobexe33() == null ? "" : value.getJobexe33();
			String jobexe24 = value.getJobexe34() == null ? "" : value.getJobexe34();
			if (jobexe21.equals("Y") | jobexe22.equals("Y") | jobexe23.equals("Y") | jobexe24.equals("Y")) {
				if (value.getOutofexe() != null) {
					String[] getdate = value.getOutofexe().split(",");
					for (String date : getdate) {
						if (!date.equals(YDMATimeUtil.getCurrentDateScheduler())) {
							System.out.println("돌아가고 있는지" + YDMATimeUtil.getCurrentTimeByNewFormat());
							for (QuestListDto dto : list) {
								List<QuestListDto> searchlist = new ArrayList<>();
								IShopOrderCommand ctx = null;
								List<QuestListDto> args = Arrays.asList(dto);

								ProductQuestionService service = new ProductQuestionService(
										IShopQuestion.getSearchBean(dto.getShopnm()));
								searchlist.addAll(service.excute(args));

								if (searchlist.size() > 0) {
									ShopQuestionDao.get().SaveOrUpdate(searchlist);
								}

							}
							System.out.println("문의수집 끝 ");
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 답변처리
	@Override
	protected int sendReply(JobExecutionContext context, ScheduleInfoDto value, List<QuestListDto> list) {
		try {
			// String dateFrom = YDMATimeUtil.getCurrentDateHanjin().concat("0000");
			List<QuestListDto> quest = new ArrayList<>();
			List<QuestListDto> datasource = new ArrayList<>();

			String startdt = list.get(0).getStartDt() + "000000";
			String enddt = list.get(0).getEndDt() + "999999";
			datasource = ShopQuestionDao.get().QuestionAnswer(startdt, enddt, "REGDM");

			List<QuestListDto> resultList = new ArrayList<QuestListDto>();
			System.out.println("주문확인시작");
			String jobexe31 = value.getJobexe41() == null ? "" : value.getJobexe41();
			String jobexe32 = value.getJobexe42() == null ? "" : value.getJobexe42();
			String jobexe33 = value.getJobexe43() == null ? "" : value.getJobexe43();
			String jobexe34 = value.getJobexe44() == null ? "" : value.getJobexe44();
			if (jobexe31.equals("Y") | jobexe32.equals("Y") | jobexe33.equals("Y") | jobexe34.equals("Y")) {
				if (value.getOutofexe() != null) {
					String[] getdate = value.getOutofexe().split(",");
					for (String date : getdate) {
						if (!date.equals(YDMATimeUtil.getCurrentDateScheduler())) {
							System.out.println("주문확인돈다1");
							if (datasource.size() > 0) {
								for (QuestListDto dto : datasource) {
									// 주문확인..
									resultList.add(dto);

									// ---- 신규 주문건만 가져온다.
									resultList = resultList.stream()
											.filter(p -> p.getQnastat().equals(QuestionStatus.답변완료))
											.collect(Collectors.toList());
									ProductQuestionService service = new ProductQuestionService(
											IShopQuestion.getEnrollmentBean(dto.getShopnm()));
									quest.addAll(service.excute(resultList));
									// --- 신규 주문건이 있으면 주문 호가인 한다.
									if (resultList != null && resultList.size() > 0) {
										ShopQuestionDao.get().SenddtUpdate(quest);
									}
									System.out.println("답변확인 끝 ");
								}
							}

						}
					}
				}
			} else {
				return 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
