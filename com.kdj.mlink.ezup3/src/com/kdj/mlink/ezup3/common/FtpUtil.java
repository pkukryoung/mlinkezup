package com.kdj.mlink.ezup3.common;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

import org.eclipse.jface.viewers.StructuredViewerInternals;
import org.eclipse.wb.swt.ResourceManager;
import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.kdj.mlink.ezup3.data.dao.ProductMstDto;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;


public class FtpUtil {

//	String remotePathDown = "";

	
	static String host;
	static Integer port;
	static String webPath;
	static String user;//
	static String password;
	static String imagepath;
	private static Session session = null;
	private static Channel channel = null;
	private static ChannelSftp channelSftp = null;

	static FtpUtil instance;

//	private Properties appProperties;
//
//	public String getAppProperty(String key) {
//		return (String) appProperties.get(key);
//	}

	public static FtpUtil getInstance() throws Exception {

		if (instance == null) {
			instance = new FtpUtil();
		}

		return instance;
	}

	public static boolean chekExistFile(String remoteDir, String fileName) throws Exception {
		
		boolean existed = false;
		
		FileInputStream fis = null;
		// 앞서 만든 접속 메서드를 사용해 접속한다.
		connect();
		try {
			// Change to output directory
			channelSftp.cd(remoteDir);
			
			Vector filelist = channelSftp.ls(remoteDir);
			for(int i=0; i<filelist.size();i++){
				LsEntry lsEntry = (LsEntry)filelist.get(i);				
				System.out.println("서버파일 리스트 :"+ lsEntry.getFilename());
				if(fileName.equals(lsEntry.getFilename())){
					existed = true;
					break;
				}
			}


		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}

			disconnect();
		}
		
		return existed;
	}

	// SFTP 서버연결 -- 내부적으로 port 22 사용함
	public static void connect() throws Exception {		
		// ftproot 경로 가져오기
		
		host = YDMAProperties.getInstance().getAppProperty("ftp.host");
		port = Integer.parseInt(YDMAProperties.getInstance().getAppProperty("ftp.port"));
		user = YDMAProperties.getInstance().getAppProperty("ftp.username");
		user =  AES256Util.getInstance(AES256Util.KEY_WORD).decrypt(user);
		password = YDMAProperties.getInstance().getAppProperty("ftp.password");
		password =  AES256Util.getInstance(AES256Util.KEY_WORD).decrypt(password);
		webPath = YDMAProperties.getInstance().getAppProperty("ftp.webpath");
		imagepath = YDMAProperties.getInstance().getAppProperty("ftp.imagepath");

//		System.out.println("connecting..." + host);
//
		JSch jsch = new JSch();
		session = jsch.getSession(user, host, port);
		session.setConfig("StrictHostKeyChecking", "no");
		session.setPassword(password);
		session.connect();
		channel = session.openChannel("sftp");
		channel.connect();
		channelSftp = (ChannelSftp) channel;

	}

	// 파일서버와 세션 종료
	public static void disconnect() {
		if (session!=null && session.isConnected()) {
			channelSftp.quit();
			channel.disconnect();
			session.disconnect();
		}
	}

	public static void uploadXmlFileToWeb(char type) throws Exception{
		
		String remoteDir = YDMAProperties.getInstance().getAppProperty("ftp.webpath");
		String templatePath = null;
		String templateFile = null; 
		if(type == 'O') {
			templatePath = YDMASessonUtil.getAppPath(YDMAProperties.getInstance().getAppProperty("sabang.orderTemplatePath"));
			templateFile = YDMAProperties.getInstance().getAppProperty("sabang.orderTempateFile");
		}else if(type == 'P') {
			templatePath = YDMASessonUtil.getAppPath(YDMAProperties.getInstance().getAppProperty("sabang.productTemplatePath"));
			templateFile = YDMAProperties.getInstance().getAppProperty("sabang.productTempateFile");	
		}else if(type == 'C') {
			templatePath = YDMASessonUtil.getAppPath(YDMAProperties.getInstance().getAppProperty("sabang.claimTemplatePath"));
			templateFile = YDMAProperties.getInstance().getAppProperty("sabang.claimTempateFile");	
		}else if(type == 'Q') {
			templatePath = YDMASessonUtil.getAppPath(YDMAProperties.getInstance().getAppProperty("sabang.questionTemplatePath"));
			templateFile = YDMAProperties.getInstance().getAppProperty("sabang.questionTempateFile");	
		}else if(type == 'S') {
			templatePath = YDMASessonUtil.getAppPath(YDMAProperties.getInstance().getAppProperty("sabang.pickupExpressSabangNetTemplatePath"));
			templateFile = YDMAProperties.getInstance().getAppProperty("sabang.pickupExpressSabangNetTempateFile");	
		} else if(type == 'T') {
			templatePath = YDMASessonUtil.getAppPath(YDMAProperties.getInstance().getAppProperty("11st.productListTemplatePath"));
			templateFile = YDMAProperties.getInstance().getAppProperty("11st.productListTempateFile");	
		}else if(type == 'M') {
			templatePath = YDMASessonUtil.getAppPath(YDMAProperties.getInstance().getAppProperty("11st.productSearchTemplatePath"));
			templateFile = YDMAProperties.getInstance().getAppProperty("11st.productSearchTempateFile");	
		}
		
	
		String templateFullpath = templatePath+File.separator+templateFile;
		
		FtpUtil.upload(remoteDir, templateFullpath);	
		
	}

	// 파일 업로드
	public static void upload(String remoteDir, String fileName) throws Exception {
		
		
		FileInputStream fis = null;
		// 앞서 만든 접속 메서드를 사용해 접속한다.
		connect();
		try {
			// Change to output directory
			channelSftp.cd(remoteDir);
			// Upload file
			File file = new File(fileName);
			// 입력 파일을 가져온다.
			fis = new FileInputStream(file);
			// 파일을 업로드한다.
			channelSftp.put(fis, file.getName());

			System.out.println("File uploaded successfully - " + file.getAbsolutePath());
	

		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}

			disconnect();
		}
	} 



	// 파일 다운로드
	public static void download(String fileName, String localDir) throws Exception {
		byte[] buffer = new byte[1024];
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			// Change to output directory
			String cdDir = fileName.substring(0, fileName.lastIndexOf("/") + 1);
			channelSftp.cd(cdDir);

			File file = new File(fileName);
			bis = new BufferedInputStream(channelSftp.get(file.getName()));

			File newFile = new File(localDir + "/" + file.getName());
			// Download file
			OutputStream os = new FileOutputStream(newFile);

			bos = new BufferedOutputStream(os);
			int readCount;
			while ((readCount = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, readCount);
			}

			System.out.println("File downloaded successfully - " + file.getAbsolutePath());

		} catch (Exception e) {
			throw e;
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
	}
	
	
	//이미지 폴더생성
	public static int uploadImage(String prodcd, String sourimage, int imgnum, List<String> comlist) throws Exception {
		
		FileInputStream fis = null;
		int result=0;

		String tagimage =prodcd+"0"+ Integer.toString(imgnum)+".jpg";
		
		connect();
		try {
			channelSftp.cd(imagepath+comlist.get(1));

			File file = new File(sourimage);
			// 입력 파일을 가져온다.
			fis = new FileInputStream(file);
			// 파일을 업로드한다.
			channelSftp.put(fis,tagimage);
		}catch(Exception e) {
			e.getMessage();
			result = 0;
		}finally {
			result = 1;
		}
		return result;
		
	}
	
	public static Vector getFtpLile(String remoteDir) throws Exception {
		
		Vector filelist = null;
		
		// 앞서 만든 접속 메서드를 사용해 접속한다.
		connect();
		
		try {
			
			// Ftp directory List
			channelSftp.cd(remoteDir);
			filelist = channelSftp.ls(remoteDir);

		} catch (Exception e) {
			throw e;
		} finally {
			disconnect();
		}
		return filelist;
	}
	
	

	public static int compareFtptoLocalFile(String remoteDir, String localDir) throws Exception {
		
		int copyCnt = 0;
		
		//프로그래스
		int Check=0;
		JFrame frame = new JFrame("저장된 파일을 가지고 오는중입니다.");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JProgressBar bar = new JProgressBar();		
		Container container = frame.getContentPane();
		container.add(bar,BorderLayout.CENTER);
		frame.setSize(570, 80);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
		frame.setLocation(screen.width/2-285,screen.height/2-40);
		
		// 앞서 만든 접속 메서드를 사용해 접속한다.
		connect();
		
		try {
			
			// Change to output directory
			channelSftp.cd(remoteDir);
			
			Vector filelist = channelSftp.ls(remoteDir);
			File folder = new File(localDir);
	        String[] filename = folder.list();
//	        File file[] = folder.listFiles();
	        
	        if(filename == null || filename.length == 0) {
	        	for(int i=2; i<filelist.size(); i++){
	        		LsEntry lsEntry = (LsEntry)filelist.get(i);
	        		if (lsEntry.getFilename().equals(".") || lsEntry.getFilename().equals("..")) {
	        			continue;
	                }
	        		downloadWithDir(remoteDir+lsEntry.getFilename(), localDir);
	        		copyCnt++;
	        		
	        		//프로그래서바실행
					if(filelist.size()>=Check) {
						bar.setMaximum(filelist.size());
						bar.setValue(Check);
						bar.setStringPainted(true);	
						frame.setVisible(true);
					} 
					Check++;
	        	}
	        } else {
				for(int i=2; i<filelist.size(); i++){
					LsEntry lsEntry = (LsEntry)filelist.get(i);	
					if (lsEntry.getFilename().equals(".") || lsEntry.getFilename().equals("..")) {
	        			continue;
	                }
					boolean existed = false; 
					for (int k=0; k<filename.length; k++) {
						if(filename[k].equals(lsEntry.getFilename())){
							
							SftpATTRS attr = lsEntry.getAttrs();	
							long modTime = attr.getMTime() * 1000L;	// convert to milliseconds
							Date firstDate = new Date(modTime); 
							
							Path path = Paths.get(localDir, filename[k]);
							FileTime lastModifiedTime = (FileTime) Files.getAttribute(path, "basic:lastModifiedTime", NOFOLLOW_LINKS);
							Date secondDate = new Date(lastModifiedTime.toMillis());

							SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
							String from = transFormat.format(firstDate);
							String to = transFormat.format(secondDate);
							
							int compare = from.compareTo(to);
							if(compare <= 0) { existed = true; }
							break;
						}
					}
					if (!existed) {
						downloadWithDir(remoteDir+lsEntry.getFilename(), localDir);
						copyCnt++;
					}
					//프로그래서바실행
					if(filelist.size()>=Check) {
						bar.setMaximum(filelist.size());
						bar.setValue(Check);
						bar.setStringPainted(true);	
						frame.setVisible(true);						
					} 
					Check++;
				}
	        }
		} catch (Exception e) {
			throw e;
		} finally {
			disconnect();
			frame.setVisible(false);
		}
		return copyCnt;
	}
	
	// 파일 다운로드
	public static void downloadWithDir(String fileName, String localDir) throws Exception {
		
		byte[] buffer = new byte[1024];
		BufferedInputStream bis = null;
		
		// Change to output directory
		String cdDir = fileName.substring(0, fileName.lastIndexOf("/") + 1);
		channelSftp.cd(cdDir);

		File file = new File(fileName);
		bis = new BufferedInputStream(channelSftp.get(file.getName()));

		File newFile = new File(localDir + "/" + file.getName());
		File parentDir = newFile.getParentFile();
	    if (!parentDir.exists()) {
	        parentDir.mkdir();
	    }
		
		// Download file
	    OutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));
	    
		try {
			int readCount;
			while ((readCount = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, readCount);
			}
			System.out.println("File downloaded successfully - " + file.getAbsolutePath());
		} catch (Exception e) {
			throw e;
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
	}
	
	//이미지로컬에 만들기
	public static void uploadLocalImage(String prodcd, String mainimg, int imgnum, List<String> comlist) {
		try {
			FileInputStream fis = null;
            //원본이미지파일의 경로+파일명
            String imagename =mainimg;
    		String SRC_FILE = "";
    		
    		BufferedImage img = null;
    		URL url = null;
    		java.awt.Image resizeImage = null;
    		BufferedImage newImage = null;
    		Graphics g = null;
    		
			SRC_FILE = "tmp1";
			try {
				if(imagename.contains("http")) {
					url = new URL(imagename);
					img = ImageIO.read(url);
				} else {
					img = ImageIO.read(new File(imagename));
				}			
				resizeImage = img.getScaledInstance(90, 85,java.awt.Image.SCALE_SMOOTH);
				newImage = new BufferedImage(90, 85, BufferedImage.TYPE_INT_RGB);
				g = newImage.getGraphics();
		        g.drawImage(resizeImage, 0, 0, null);
		        g.dispose();
		        ImageIO.write(newImage, "jpg", new File(SRC_FILE));
		        String tagimage =prodcd+".jpg";
		        
                connect();
		        channelSftp.cd(imagepath+comlist.get(1)+"/thumb2/");
		        
//		        System.out.println(imagepath+comlist.get(1));
		        
    			File file = new File(SRC_FILE);
    			// 입력 파일을 가져온다.
    			fis = new FileInputStream(file);
    			// 파일을 업로드한다.
    			channelSftp.put(fis,tagimage);
    			if(imagename.contains("http")) {
					url = new URL(imagename);
					img = ImageIO.read(url);
				} else {
					img = ImageIO.read(new File(imagename));
				}			
				resizeImage = img.getScaledInstance(25, 25,java.awt.Image.SCALE_SMOOTH);
				newImage = new BufferedImage(25, 25, BufferedImage.TYPE_INT_RGB);
				g = newImage.getGraphics();
		        g.drawImage(resizeImage, 0, 0, null);
		        g.dispose();
		        ImageIO.write(newImage, "jpg", new File(SRC_FILE));

                connect();
		        channelSftp.cd(imagepath+comlist.get(1)+"/thumb1/");

    			file = new File(SRC_FILE);
    			// 입력 파일을 가져온다.
    			fis = new FileInputStream(file);
    			// 파일을 업로드한다.
    			channelSftp.put(fis,tagimage);
			} catch (Exception e) {
				e.printStackTrace();
			}
                System.out.println("썸네일 생성완료2");
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}