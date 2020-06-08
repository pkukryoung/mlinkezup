package com.kdj.mlink.ezup3.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import com.kdj.mlink.ezup3.shop.dao.ShopOptionProductInDto;
import com.kdj.mlink.ezup3.common.YDMASessonUtil;
import com.kdj.mlink.ezup3.common.YDMATimeUtil;
import com.kdj.mlink.ezup3.data.dao.DBCPInit;

public class ScheduleInfoDao {
	
	public ScheduleInfoDto getScheduleInfoByJobcd(int compno, int jobcd)  throws  Exception {

        ScheduleInfoDto list = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "select COMPNO,JOBCD,ifnull(JOBSTAT,''),ifnull(EXECID,''),ifnull(EXECDT,''),"
            		   + "       ifnull(weekchk1,''),ifnull(fewminu1,''),ifnull(jobexe11,''),ifnull(jobexe21,''),ifnull(jobexe31,''),ifnull(jobexe41,''),"
            		   + "       ifnull(weekchk2,''),ifnull(fewminu2,''),ifnull(jobexe12,''),ifnull(jobexe22,''),ifnull(jobexe32,''),ifnull(jobexe42,''),"
            		   + "       ifnull(weekchk3,''),ifnull(fewminu3,''),ifnull(jobexe13,''),ifnull(jobexe23,''),ifnull(jobexe33,''),ifnull(jobexe43,''),"
            		   + "       ifnull(weekchk4,''),ifnull(fewminu4,''),ifnull(jobexe14,''),ifnull(jobexe24,''),ifnull(jobexe34,''),ifnull(jobexe44,''),"
            		   + "       ifnull(SITECHK,''),ifnull(TYPECHK,''),ifnull(MAXCHK,''),ifnull(MAXCNT,0)," 
            		   + "	     ifnull(EXCEPCHK1,''),ifnull(EXCEPS1,''),ifnull(EXCEPE1,''),ifnull(EXCEPCHK2,''),ifnull(EXCEPS2,''),ifnull(EXCEPE2,''),ifnull(EXCEPCHK3,''),ifnull(EXCEPS3,''),ifnull(EXCEPE3,'')," 
            		   + "       ifnull(OUTOFEXE,''),ifnull(SMSCHK,''),ifnull(SMSNO,''),ifnull(DONTCHK,''),ifnull(DONTSTR,''),ifnull(DONTEND,''),ifnull(EMAILCHK,''),ifnull(EMAILADDR,'')"  
            		   + "  FROM scheduinfo "  
            		   + " WHERE COMPNO = ? "  
            		   + "   AND JOBCD = ? ";

            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, compno);
            pstmt.setInt(2, jobcd);

            System.out.println("[getScheduleInfoByJobcd]"+pstmt.toString());
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
				int rowIdx = 0;
				ScheduleInfoDto dto = new ScheduleInfoDto();
				dto.setCompno(rs.getInt(++rowIdx));
				dto.setJobcd(rs.getInt(++rowIdx));
				dto.setJobstat(rs.getString(++rowIdx));
				dto.setExecid(rs.getString(++rowIdx));
				dto.setExecdt(rs.getString(++rowIdx));
				dto.setWeekchk1(rs.getString(++rowIdx));
				dto.setFewminu1(rs.getString(++rowIdx));
				dto.setJobexe11(rs.getString(++rowIdx));
				dto.setJobexe21(rs.getString(++rowIdx));
				dto.setJobexe31(rs.getString(++rowIdx));
				dto.setJobexe41(rs.getString(++rowIdx));
				dto.setWeekchk2(rs.getString(++rowIdx));
				dto.setFewminu2(rs.getString(++rowIdx));
				dto.setJobexe12(rs.getString(++rowIdx));
				dto.setJobexe22(rs.getString(++rowIdx));
				dto.setJobexe32(rs.getString(++rowIdx));
				dto.setJobexe42(rs.getString(++rowIdx));
				dto.setWeekchk3(rs.getString(++rowIdx));
				dto.setFewminu3(rs.getString(++rowIdx));
				dto.setJobexe13(rs.getString(++rowIdx));
				dto.setJobexe23(rs.getString(++rowIdx));
				dto.setJobexe33(rs.getString(++rowIdx));
				dto.setJobexe43(rs.getString(++rowIdx));
				dto.setWeekchk4(rs.getString(++rowIdx));
				dto.setFewminu4(rs.getString(++rowIdx));
				dto.setJobexe14(rs.getString(++rowIdx));
				dto.setJobexe24(rs.getString(++rowIdx));
				dto.setJobexe34(rs.getString(++rowIdx));
				dto.setJobexe44(rs.getString(++rowIdx));
				dto.setSitechk(rs.getString(++rowIdx));
				dto.setTypechk(rs.getString(++rowIdx));
				dto.setMaxchk(rs.getString(++rowIdx));
				dto.setMaxcnt(Integer.parseInt(rs.getString(++rowIdx)));
				dto.setExcepchk1(rs.getString(++rowIdx));
				dto.setExceps1(rs.getString(++rowIdx));
				dto.setExcepe1(rs.getString(++rowIdx));
				dto.setExcepchk2(rs.getString(++rowIdx));
				dto.setExceps2(rs.getString(++rowIdx));
				dto.setExcepe2(rs.getString(++rowIdx));
				dto.setExcepchk3(rs.getString(++rowIdx));
				dto.setExceps3(rs.getString(++rowIdx));
				dto.setExcepe3(rs.getString(++rowIdx));		
				dto.setOutofexe(rs.getString(++rowIdx));
				dto.setSmschk(rs.getString(++rowIdx));
				dto.setSmsno(rs.getString(++rowIdx));
				dto.setDontchk(rs.getString(++rowIdx));
				dto.setDontstr(rs.getString(++rowIdx));
				dto.setDontend(rs.getString(++rowIdx));
				dto.setEmailchk(rs.getString(++rowIdx));
				dto.setEmailaddr(rs.getString(++rowIdx));
				
				list = dto;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
        }

        return list;
    }
	
	public List<List<String>> getScheduleSiteByJobcd(int compno, int jobcd)  throws  Exception {

		List<List<String>> contents = null;

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "select COMPNO,JOBCD,SHOPCD,SHOPSEQ" 
            		      + " FROM schedusite "  
            		      + "WHERE COMPNO = ? "  
            		      + "   AND JOBCD = ? ";

            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, compno);
            pstmt.setInt(2, jobcd);

            System.out.println("[getScheduleSiteByJobcd]"+pstmt.toString());
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
				int rowIdx = 0;
				List <String> list = new ArrayList<>();
				list.add(rs.getString(++rowIdx)); 
				list.add(rs.getString(++rowIdx)); 
				list.add(rs.getString(++rowIdx)); 
				list.add(rs.getString(++rowIdx)); 
				
                contents.add(list);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
        }
        return contents;
    }
    
    public int insertScheduleInfo(ScheduleInfoDto dto) throws Exception {

    	int result = 0;

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "insert into Scheduinfo "
            		   + "(COMPNO,JOBCD,JOBSTAT,EXECID,EXECDT, "
            		   + " weekchk1,fewminu1,jobexe11,jobexe21,jobexe31,jobexe41, " 
            		   + " weekchk2,fewminu2,jobexe12,jobexe22,jobexe32,jobexe42, " 
            		   + " weekchk3,fewminu3,jobexe13,jobexe23,jobexe33,jobexe43, "
            		   + " weekchk4,fewminu4,jobexe14,jobexe24,jobexe34,jobexe44, "
            		   + " SITECHK,TYPECHK,MAXCHK,MAXCNT, " 
            		   + " EXCEPCHK1,EXCEPS1,EXCEPE1,EXCEPCHK2,EXCEPS2,EXCEPE2,EXCEPCHK3,EXCEPS3,EXCEPE3, "
            		   + " OUTOFEXE,SMSCHK,SMSNO,DONTCHK,DONTSTR,DONTEND,EMAILCHK,EMAILADDR) "
            		   + " values "
            		   + "(?, ?, ?, ?, ?, "
            		   + " ?, ?, ?, ?, ?, ?, "
            		   + " ?, ?, ?, ?, ?, ?, "
            		   + " ?, ?, ?, ?, ?, ?, "
            		   + " ?, ?, ?, ?, ?, ?, "
            		   + " ?, ?, ?, ?, "
            		   + " ?, ?, ?, ?, ?, ?, ?, ?, ?, "
            		   + " ?, ?, ?, ?, ?, ?, ?, ? )";
            
            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            
			int rowIdx = 0;	
			pstmt.setInt(++rowIdx, dto.getCompno());
			pstmt.setInt(++rowIdx, dto.getJobcd());
			pstmt.setString(++rowIdx, dto.getJobstat());
			pstmt.setString(++rowIdx, dto.getExecid());
			pstmt.setString(++rowIdx, dto.getExecdt()); 
			
			pstmt.setString(++rowIdx, dto.getWeekchk1());
			pstmt.setString(++rowIdx, dto.getFewminu1());
			pstmt.setString(++rowIdx, dto.getJobexe11());
			pstmt.setString(++rowIdx, dto.getJobexe21());
			pstmt.setString(++rowIdx, dto.getJobexe31());
			pstmt.setString(++rowIdx, dto.getJobexe41());
			pstmt.setString(++rowIdx, dto.getWeekchk2());
			pstmt.setString(++rowIdx, dto.getFewminu2());
			pstmt.setString(++rowIdx, dto.getJobexe12());
			pstmt.setString(++rowIdx, dto.getJobexe22());
			pstmt.setString(++rowIdx, dto.getJobexe32());
			pstmt.setString(++rowIdx, dto.getJobexe42());
			pstmt.setString(++rowIdx, dto.getWeekchk3());
			pstmt.setString(++rowIdx, dto.getFewminu3());
			pstmt.setString(++rowIdx, dto.getJobexe13());
			pstmt.setString(++rowIdx, dto.getJobexe23());
			pstmt.setString(++rowIdx, dto.getJobexe33());
			pstmt.setString(++rowIdx, dto.getJobexe43());
			pstmt.setString(++rowIdx, dto.getWeekchk4());
			pstmt.setString(++rowIdx, dto.getFewminu4());
			pstmt.setString(++rowIdx, dto.getJobexe14());
			pstmt.setString(++rowIdx, dto.getJobexe24());
			pstmt.setString(++rowIdx, dto.getJobexe34());
			pstmt.setString(++rowIdx, dto.getJobexe44());
			
			pstmt.setString(++rowIdx, dto.getSitechk());
			pstmt.setString(++rowIdx, dto.getTypechk());
			pstmt.setString(++rowIdx, dto.getMaxchk());
			pstmt.setInt(++rowIdx, dto.getMaxcnt()); 
			pstmt.setString(++rowIdx, dto.getExcepchk1());
			pstmt.setString(++rowIdx, dto.getExceps1());
			pstmt.setString(++rowIdx, dto.getExcepe1());
			pstmt.setString(++rowIdx, dto.getExcepchk2());
			pstmt.setString(++rowIdx, dto.getExceps2());
			pstmt.setString(++rowIdx, dto.getExcepe2());
			pstmt.setString(++rowIdx, dto.getExcepchk3());
			pstmt.setString(++rowIdx, dto.getExceps3());
			pstmt.setString(++rowIdx, dto.getExcepe3());
			pstmt.setString(++rowIdx, dto.getOutofexe());
			pstmt.setString(++rowIdx, dto.getSmschk());
			pstmt.setString(++rowIdx, dto.getSmsno());
			pstmt.setString(++rowIdx, dto.getDontchk()); 
			pstmt.setString(++rowIdx, dto.getDontstr()); 
			pstmt.setString(++rowIdx, dto.getDontend()); 
			pstmt.setString(++rowIdx, dto.getEmailchk()); 
			pstmt.setString(++rowIdx, dto.getEmailaddr()); 
				
			System.out.println("[insertScheduleInfo]"+pstmt.toString());

			result = pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, null);
		}
        return result;
    }
    
    public int updateScheduleInfoByJobcd(ScheduleInfoDto dto) throws Exception {

        int result = 0;

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "update ScheduInfo "
            		   + "   set jobstat=?, execid=?, execdt=?, "
            		   + "       weekchk1=?, fewminu1=?, jobexe11=?, jobexe21=?, jobexe31=?, jobexe41=?,"
            		   + "       weekchk2=?, fewminu2=?, jobexe12=?, jobexe22=?, jobexe32=?, jobexe42=?,"
            		   + "       weekchk3=?, fewminu3=?, jobexe13=?, jobexe23=?, jobexe33=?, jobexe43=?,"
            		   + "       weekchk4=?, fewminu4=?, jobexe14=?, jobexe24=?, jobexe34=?, jobexe44=?,"
            		   + "		 sitechk=?, typechk=?, maxchk=?, maxcnt=?,"
            		   + "       excepchk1=?, exceps1=?, excepe1=?, excepchk2=?, exceps2=?, excepe2=?, excepchk3=?, exceps3=?, excepe3=?,"
            		   + "       outofexe=?, smschk=?, smsno=?, dontchk=?, dontstr=?, dontend=?, emailchk=?, emailaddr=?"
             		   + " where compno= ? "
             		   + "   and jobcd = ? ";
            
            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            
            int rowIdx = 0;	
            pstmt.setString(++rowIdx, dto.getJobstat()); 
            pstmt.setString(++rowIdx, dto.getExecid());
            pstmt.setString(++rowIdx, dto.getExecdt());
            
            pstmt.setString(++rowIdx, dto.getWeekchk1());
			pstmt.setString(++rowIdx, dto.getFewminu1());
			pstmt.setString(++rowIdx, dto.getJobexe11());
			pstmt.setString(++rowIdx, dto.getJobexe21());
			pstmt.setString(++rowIdx, dto.getJobexe31());
			pstmt.setString(++rowIdx, dto.getJobexe41());
			pstmt.setString(++rowIdx, dto.getWeekchk2());
			pstmt.setString(++rowIdx, dto.getFewminu2());
			pstmt.setString(++rowIdx, dto.getJobexe12());
			pstmt.setString(++rowIdx, dto.getJobexe22());
			pstmt.setString(++rowIdx, dto.getJobexe32());
			pstmt.setString(++rowIdx, dto.getJobexe42());
			pstmt.setString(++rowIdx, dto.getWeekchk3());
			pstmt.setString(++rowIdx, dto.getFewminu3());
			pstmt.setString(++rowIdx, dto.getJobexe13());
			pstmt.setString(++rowIdx, dto.getJobexe23());
			pstmt.setString(++rowIdx, dto.getJobexe33());
			pstmt.setString(++rowIdx, dto.getJobexe43());
			pstmt.setString(++rowIdx, dto.getWeekchk4());
			pstmt.setString(++rowIdx, dto.getFewminu4());
			pstmt.setString(++rowIdx, dto.getJobexe14());
			pstmt.setString(++rowIdx, dto.getJobexe24());
			pstmt.setString(++rowIdx, dto.getJobexe34());
			pstmt.setString(++rowIdx, dto.getJobexe44());
			
			pstmt.setString(++rowIdx, dto.getSitechk());
			pstmt.setString(++rowIdx, dto.getTypechk());
			pstmt.setString(++rowIdx, dto.getMaxchk());
			pstmt.setInt(++rowIdx, dto.getMaxcnt()); 
			pstmt.setString(++rowIdx, dto.getExcepchk1());
			pstmt.setString(++rowIdx, dto.getExceps1());
			pstmt.setString(++rowIdx, dto.getExcepe1());
			pstmt.setString(++rowIdx, dto.getExcepchk2());
			pstmt.setString(++rowIdx, dto.getExceps2());
			pstmt.setString(++rowIdx, dto.getExcepe2());
			pstmt.setString(++rowIdx, dto.getExcepchk3());
			pstmt.setString(++rowIdx, dto.getExceps3());
			pstmt.setString(++rowIdx, dto.getExcepe3());
			pstmt.setString(++rowIdx, dto.getOutofexe());
			pstmt.setString(++rowIdx, dto.getSmschk());
			pstmt.setString(++rowIdx, dto.getSmsno());
			pstmt.setString(++rowIdx, dto.getDontchk()); 
			pstmt.setString(++rowIdx, dto.getDontstr()); 
			pstmt.setString(++rowIdx, dto.getDontend()); 
			pstmt.setString(++rowIdx, dto.getEmailchk()); 
			pstmt.setString(++rowIdx, dto.getEmailaddr()); 
			
			pstmt.setInt(++rowIdx, dto.getCompno()); 
			pstmt.setInt(++rowIdx, dto.getJobcd()); 
            
            System.out.println("[updateScheduleInfoByJobcd]"+pstmt.toString());

            result = pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
        }

        return result;
        
    }

	public boolean isExistScheduler(String compno, int jobcd) throws Exception {

		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select jobstat from scheduinfo where COMPNO = ? and JOBCD = ? ";
			sql = sql.toUpperCase();			
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(compno));
			pstmt.setInt(2, jobcd);
			System.out.println("[isExistProduct]"+pstmt.toString());
			
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return flag;
	}

	public int scheduleSetUpdateByShopSeq(int jobcd, String shopseq) throws Exception {

        int result = 0;

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "update ScheduInfo "
            		   + "   set shopseq = ? "
             		   + " where compno= ? "
             		   + "   and jobcd = ? ";
            
            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            
            int rowIdx = 0;	
            pstmt.setString(++rowIdx, shopseq); 
            pstmt.setString(++rowIdx, YDMASessonUtil.getCompnoInfo().getCompno());
            pstmt.setInt(++rowIdx, jobcd);          
            
            System.out.println("[scheduleSetUpdateByShopSeq]"+pstmt.toString());

            result = pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
        }

        return result;
		
	}
	//스케쥴에서 shopdtl에 아이디랑 비번가지고 오기
	public List<ShopOrderMstDto> getShopDetailList(String shopseq) throws Exception {
		List<ShopOrderMstDto> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			//for(int k =1;k<=list.size()-1;k++) {
			String sql = String.format("SELECT ifnull(shopcd,''), ifnull(SHOPSEQ,''), ifnull(SHOPPINGID,''),ifnull(PASSWORD,''), ifnull(AUTHKEY1,''), ifnull(AUTHKEY2,''), ifnull(APIKEY,'') "
					+ "FROM shopdtl WHERE SHOPSEQ in(%s)", shopseq)
					+ " AND compno = ?";

			sql = sql.toUpperCase();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());

			System.out.println("[getShopDetailList]" + pstmt.toString());

			rs = pstmt.executeQuery();

			int rowNum = 0;
			while (rs.next()) {
				ShopOrderMstDto dto = new ShopOrderMstDto();
				int columnIndex = 0;
				rowNum++;
				dto.setShopid(rs.getString(++columnIndex));
				dto.setShopseq(rs.getString(++columnIndex));
				dto.setShop_userid(rs.getString(++columnIndex));
				dto.setShopPw(rs.getString(++columnIndex));
				dto.setAuthkey1(rs.getString(++columnIndex));
				dto.setAuthKey2(rs.getString(++columnIndex));
				dto.setApikey(rs.getString(++columnIndex));
				dto.setStartDt(YDMATimeUtil.getCurrentDateHanjin().concat("0000"));
				dto.setEndDt(YDMATimeUtil.getCurrentTime().substring(0, 12));
				
				list.add(dto);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return list;
	}
	//특정사이트설정
	public int scheduleSetInsertByShopSeq(int jobcd, List<String> shopseq, List<String> shopcd) throws Exception {

        int result = 0;
        List<PreparedStatement> statementlist = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "insert into schedusite (COMPNO,JOBCD,SHOPCD,SHOPSEQ) values(?, ?, ?, ?)";

    		pstmt = connection.prepareStatement(sql);
    		statementlist.add(pstmt);

    		for (int k = 0;k<shopseq.size();k++) {
    			boolean flag = isExistJobcd(jobcd);
    			if(flag) {
    				scheduSiteDelete(jobcd);
    			}
    			int i = 0;
    			pstmt.setString(++i, YDMASessonUtil.getCompnoInfo().getCompno());
    			pstmt.setInt(++i, jobcd);
    			pstmt.setString(++i, shopcd.get(k));
    			pstmt.setString(++i, shopseq.get(k));
    			System.out.println("[deleteOrder]" + pstmt.toString());

    			pstmt.addBatch();
    			pstmt.clearParameters(); // 파라미터 초기화
    		}

    		pstmt.executeBatch();
    		pstmt.clearBatch();// Batch 초기화

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
        }

        return result;
	}
	//삭제하기
	private void scheduSiteDelete(int jobcd) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "delete from schedusite where COMPNO = ? and JOBCD = ? ";
			sql = sql.toUpperCase();			
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setInt(2, jobcd);
			
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}
		
	}
	//있는지없는지 유무
	private boolean isExistJobcd(int jobcd) throws Exception {
		boolean flag = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			connection = DBCPInit.getInstance().getConnection();

			String sql = "select JOBCD from schedusite where COMPNO = ? and JOBCD = ? ";
			sql = sql.toUpperCase();			
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());
			pstmt.setInt(2, jobcd);
			System.out.println("[isExistProduct]"+pstmt.toString());
			
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
		}

		return flag;
	}

	public List<ScheduleInfoDto> getScheduleInfoNSite(int compno, int jobcd) throws Exception {
		
        List<ScheduleInfoDto> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "select a.COMPNO,a.JOBCD,ifnull(a.JOBSTAT,''),ifnull(a.EXECID,''),ifnull(a.EXECDT,''), ifnull(a.weekchk1,''),ifnull(a.fewminu1,''),ifnull(a.jobexe11,''),ifnull(a.jobexe21,''),"
            		   + " ifnull(a.jobexe31,''),ifnull(a.jobexe41,''), ifnull(a.weekchk2,''),ifnull(a.fewminu2,''),ifnull(a.jobexe12,''),ifnull(a.jobexe22,''),ifnull(a.jobexe32,''),ifnull(a.jobexe42,''),"
            		   + " ifnull(a.weekchk3,''),ifnull(a.fewminu3,''),ifnull(a.jobexe13,''),ifnull(a.jobexe23,''),ifnull(a.jobexe33,''),ifnull(a.jobexe43,''), ifnull(a.weekchk4,''),ifnull(a.fewminu4,''),"
            		   + " ifnull(a.jobexe14,''),ifnull(a.jobexe24,''),ifnull(a.jobexe34,''),ifnull(a.jobexe44,''), ifnull(a.SITECHK,''),ifnull(a.TYPECHK,''),ifnull(a.MAXCHK,''),ifnull(a.MAXCNT,0)," 
            		   + " ifnull(a.EXCEPCHK1,''),ifnull(a.EXCEPS1,''),ifnull(a.EXCEPE1,''),ifnull(a.EXCEPCHK2,''),ifnull(a.EXCEPS2,''),ifnull(a.EXCEPE2,''),ifnull(a.EXCEPCHK3,''),ifnull(a.EXCEPS3,''),"
            		   + " ifnull(a.EXCEPE3,''),ifnull(a.OUTOFEXE,''),ifnull(a.SMSCHK,''),ifnull(a.SMSNO,''),ifnull(a.DONTCHK,''),ifnull(a.DONTSTR,''),ifnull(a.DONTEND,''),ifnull(a.EMAILCHK,''),"
            		   + " ifnull(a.EMAILADDR,''), ifnull(b.shopseq,'' ) "  
            		   + "  FROM scheduinfo a LEFT join schedusite b on a.compno = b.compno and a.jobcd = b.jobcd "  
            		   + " WHERE a.COMPNO = ? "  
            		   + "   AND a.JOBCD = ? ";

            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, compno);
            pstmt.setInt(2, jobcd);

            System.out.println("[getScheduleInfoByJobcd]"+pstmt.toString());
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
				int rowIdx = 0;
				ScheduleInfoDto dto = new ScheduleInfoDto();
				dto.setCompno(rs.getInt(++rowIdx));
				dto.setJobcd(rs.getInt(++rowIdx));
				dto.setJobstat(rs.getString(++rowIdx));
				dto.setExecid(rs.getString(++rowIdx));
				dto.setExecdt(rs.getString(++rowIdx));
				dto.setWeekchk1(rs.getString(++rowIdx));
				dto.setFewminu1(rs.getString(++rowIdx));
				dto.setJobexe11(rs.getString(++rowIdx));
				dto.setJobexe21(rs.getString(++rowIdx));
				dto.setJobexe31(rs.getString(++rowIdx));
				dto.setJobexe41(rs.getString(++rowIdx));
				dto.setWeekchk2(rs.getString(++rowIdx));
				dto.setFewminu2(rs.getString(++rowIdx));
				dto.setJobexe12(rs.getString(++rowIdx));
				dto.setJobexe22(rs.getString(++rowIdx));
				dto.setJobexe32(rs.getString(++rowIdx));
				dto.setJobexe42(rs.getString(++rowIdx));
				dto.setWeekchk3(rs.getString(++rowIdx));
				dto.setFewminu3(rs.getString(++rowIdx));
				dto.setJobexe13(rs.getString(++rowIdx));
				dto.setJobexe23(rs.getString(++rowIdx));
				dto.setJobexe33(rs.getString(++rowIdx));
				dto.setJobexe43(rs.getString(++rowIdx));
				dto.setWeekchk4(rs.getString(++rowIdx));
				dto.setFewminu4(rs.getString(++rowIdx));
				dto.setJobexe14(rs.getString(++rowIdx));
				dto.setJobexe24(rs.getString(++rowIdx));
				dto.setJobexe34(rs.getString(++rowIdx));
				dto.setJobexe44(rs.getString(++rowIdx));
				dto.setSitechk(rs.getString(++rowIdx));
				dto.setTypechk(rs.getString(++rowIdx));
				dto.setMaxchk(rs.getString(++rowIdx));
				dto.setMaxcnt(Integer.parseInt(rs.getString(++rowIdx)));
				dto.setExcepchk1(rs.getString(++rowIdx));
				dto.setExceps1(rs.getString(++rowIdx));
				dto.setExcepe1(rs.getString(++rowIdx));
				dto.setExcepchk2(rs.getString(++rowIdx));
				dto.setExceps2(rs.getString(++rowIdx));
				dto.setExcepe2(rs.getString(++rowIdx));
				dto.setExcepchk3(rs.getString(++rowIdx));
				dto.setExceps3(rs.getString(++rowIdx));
				dto.setExcepe3(rs.getString(++rowIdx));		
				dto.setOutofexe(rs.getString(++rowIdx));
				dto.setSmschk(rs.getString(++rowIdx));
				dto.setSmsno(rs.getString(++rowIdx));
				dto.setDontchk(rs.getString(++rowIdx));
				dto.setDontstr(rs.getString(++rowIdx));
				dto.setDontend(rs.getString(++rowIdx));
				dto.setEmailchk(rs.getString(++rowIdx));
				dto.setEmailaddr(rs.getString(++rowIdx));
				dto.setShopseq(rs.getString(++rowIdx));
				
				list.add(dto);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
        }

        return list;
	}
	//전체 가지고 오기
	public List<ScheduleInfoDto> getJobcd() throws Exception {

		List<ScheduleInfoDto> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DBCPInit.getInstance().getConnection();
            String sql = "select COMPNO,JOBCD,ifnull(JOBSTAT,''),ifnull(EXECID,''),ifnull(EXECDT,''),"
            		   + "       ifnull(weekchk1,''),ifnull(fewminu1,''),ifnull(jobexe11,''),ifnull(jobexe21,''),ifnull(jobexe31,''),ifnull(jobexe41,''),"
            		   + "       ifnull(weekchk2,''),ifnull(fewminu2,''),ifnull(jobexe12,''),ifnull(jobexe22,''),ifnull(jobexe32,''),ifnull(jobexe42,''),"
            		   + "       ifnull(weekchk3,''),ifnull(fewminu3,''),ifnull(jobexe13,''),ifnull(jobexe23,''),ifnull(jobexe33,''),ifnull(jobexe43,''),"
            		   + "       ifnull(weekchk4,''),ifnull(fewminu4,''),ifnull(jobexe14,''),ifnull(jobexe24,''),ifnull(jobexe34,''),ifnull(jobexe44,''),"
            		   + "       ifnull(SITECHK,''),ifnull(TYPECHK,''),ifnull(MAXCHK,''),ifnull(MAXCNT,0)," 
            		   + "	     ifnull(EXCEPCHK1,''),ifnull(EXCEPS1,''),ifnull(EXCEPE1,''),ifnull(EXCEPCHK2,''),ifnull(EXCEPS2,''),ifnull(EXCEPE2,''),ifnull(EXCEPCHK3,''),ifnull(EXCEPS3,''),ifnull(EXCEPE3,'')," 
            		   + "       ifnull(OUTOFEXE,''),ifnull(SMSCHK,''),ifnull(SMSNO,''),ifnull(DONTCHK,''),ifnull(DONTSTR,''),ifnull(DONTEND,''),ifnull(EMAILCHK,''),ifnull(EMAILADDR,'')"  
            		   + "  FROM scheduinfo "  
            		   + " WHERE COMPNO = ? ";


            sql = sql.toUpperCase();
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, YDMASessonUtil.getCompnoInfo().getCompno());


            System.out.println("[getScheduleInfoByJobcd]"+pstmt.toString());
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
				int rowIdx = 0;
				ScheduleInfoDto dto = new ScheduleInfoDto();
				dto.setCompno(rs.getInt(++rowIdx));
				dto.setJobcd(rs.getInt(++rowIdx));
				dto.setJobstat(rs.getString(++rowIdx));
				dto.setExecid(rs.getString(++rowIdx));
				dto.setExecdt(rs.getString(++rowIdx));
				dto.setWeekchk1(rs.getString(++rowIdx));
				dto.setFewminu1(rs.getString(++rowIdx));
				dto.setJobexe11(rs.getString(++rowIdx));
				dto.setJobexe21(rs.getString(++rowIdx));
				dto.setJobexe31(rs.getString(++rowIdx));
				dto.setJobexe41(rs.getString(++rowIdx));
				dto.setWeekchk2(rs.getString(++rowIdx));
				dto.setFewminu2(rs.getString(++rowIdx));
				dto.setJobexe12(rs.getString(++rowIdx));
				dto.setJobexe22(rs.getString(++rowIdx));
				dto.setJobexe32(rs.getString(++rowIdx));
				dto.setJobexe42(rs.getString(++rowIdx));
				dto.setWeekchk3(rs.getString(++rowIdx));
				dto.setFewminu3(rs.getString(++rowIdx));
				dto.setJobexe13(rs.getString(++rowIdx));
				dto.setJobexe23(rs.getString(++rowIdx));
				dto.setJobexe33(rs.getString(++rowIdx));
				dto.setJobexe43(rs.getString(++rowIdx));
				dto.setWeekchk4(rs.getString(++rowIdx));
				dto.setFewminu4(rs.getString(++rowIdx));
				dto.setJobexe14(rs.getString(++rowIdx));
				dto.setJobexe24(rs.getString(++rowIdx));
				dto.setJobexe34(rs.getString(++rowIdx));
				dto.setJobexe44(rs.getString(++rowIdx));
				dto.setSitechk(rs.getString(++rowIdx));
				dto.setTypechk(rs.getString(++rowIdx));
				dto.setMaxchk(rs.getString(++rowIdx));
				dto.setMaxcnt(Integer.parseInt(rs.getString(++rowIdx)));
				dto.setExcepchk1(rs.getString(++rowIdx));
				dto.setExceps1(rs.getString(++rowIdx));
				dto.setExcepe1(rs.getString(++rowIdx));
				dto.setExcepchk2(rs.getString(++rowIdx));
				dto.setExceps2(rs.getString(++rowIdx));
				dto.setExcepe2(rs.getString(++rowIdx));
				dto.setExcepchk3(rs.getString(++rowIdx));
				dto.setExceps3(rs.getString(++rowIdx));
				dto.setExcepe3(rs.getString(++rowIdx));		
				dto.setOutofexe(rs.getString(++rowIdx));
				dto.setSmschk(rs.getString(++rowIdx));
				dto.setSmsno(rs.getString(++rowIdx));
				dto.setDontchk(rs.getString(++rowIdx));
				dto.setDontstr(rs.getString(++rowIdx));
				dto.setDontend(rs.getString(++rowIdx));
				dto.setEmailchk(rs.getString(++rowIdx));
				dto.setEmailaddr(rs.getString(++rowIdx));
				
				list.add(dto);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            DBCPInit.getInstance().freeConnection(connection, pstmt, rs);
        }

        return list;
	}

}
