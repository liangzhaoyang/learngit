package net.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.dao.AlarmDao;
import net.domain.Alarm;
import net.domain.DataFig;
import net.utils.JdbcUtils;
import net.web.page.CriteriaObj;
import net.web.page.Page;

public class AlarmDaoImpl implements AlarmDao{

	@Override
	public Page<Alarm> getPage(CriteriaObj cb) {
		Page<Alarm> page = new Page<>(cb.getPageNo());	
		page.setTotalItemNumber(getTotalAlarmNumber(cb));
		//校验 pageNo 的合法性
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, 15));
		
		return page;
	}

	@Override
	public long getTotalAlarmNumber(CriteriaObj cb) {

		String sql="SELECT count(*) FROM alert WHERE timestamp >="+"'"+Timestamp.valueOf(cb.getStart_time())+"'"+"and timestamp<="+"'"+Timestamp.valueOf(cb.getEnd_time())+"'";
		
		long totalNum = 0;
		Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps = null;
        try {
        	conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			 
			 while(rs.next()){
	        	 
				 totalNum = rs.getInt(1);
	         }  
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(ps);
			JdbcUtils.close(conn);
		}
        return totalNum;
	}

	@Override
	public List<Alarm> getPageList(CriteriaObj cb, int pageSize) {

		String sql="SELECT Id, src, dst, sport, dport, proto, alert_msg, attack_type, severity, timestamp FROM alert WHERE timestamp >="+"'"+Timestamp.valueOf(cb.getStart_time())+"'"+"and timestamp<="+"'"+Timestamp.valueOf(cb.getEnd_time())+"'" +"LIMIT ?, ?";
		
		Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps = null;
        try {
        	conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (cb.getPageNo() - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			 
		    List<Alarm> alarmList = new ArrayList<Alarm>();
		    
			 while(rs.next()){
				 Date d=new Date();
				 d=rs.getTimestamp("timestamp");
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			     Date date= new Date(d.getTime());
				 Alarm  alarm = new Alarm(rs.getInt("Id"),long2IP(rs.getLong("src")),long2IP(rs.getLong("dst")),rs.getInt("sport"),rs.getInt("dport"),rs.getString("alert_msg"),rs.getString("attack_type"),rs.getInt("severity"),rs.getInt("proto"),sdf.format(date));
				 alarmList.add(alarm); 
	         }  
			 return alarmList;
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(ps);
			JdbcUtils.close(conn);
		}
		
       return null;
	}

	public static String long2IP(long longIP){ 
        StringBuffer sb=new StringBuffer(""); 
        //直接右移24位 
        sb.append(String.valueOf(longIP>>>24)); 
        sb.append(".");         
        //将高8位置0，然后右移16位 
        sb.append(String.valueOf((longIP&0x00FFFFFF)>>>16)); 
        sb.append("."); 
        sb.append(String.valueOf((longIP&0x0000FFFF)>>>8)); 
        sb.append("."); 
        sb.append(String.valueOf(longIP&0x000000FF)); 
        return sb.toString(); 
   }
	
	public List<DataFig> getDataSet(String start_time, String end_time) {
		String sql="SELECT count(*),timestamp FROM alert WHERE timestamp >="+"'"+Timestamp.valueOf(start_time)+"'"+"and timestamp<="+"'"+Timestamp.valueOf(end_time)+"'" +"group by timestamp order by timestamp asc";		
		Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps = null;
        
        long s_time=getHaoMiao(start_time);
		long e_time=getHaoMiao(end_time);
        
        List<DataFig> dataFigList = new ArrayList<DataFig>();
        try {
        	conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
	
			int num=0;
			long interval=30*1000;
			DataFig dataFig=new DataFig();
			 while(rs.next()){
				 long tempMs=getHaoMiao2(rs.getTimestamp(2));
	        		if(tempMs>=s_time && tempMs<s_time+interval){
	        			num+=rs.getInt(1);  
	        		}
	        		else if(tempMs>=s_time+interval){
	        			s_time+=interval;
	        			dataFig=new DataFig(num,new Timestamp(s_time));
	        			dataFigList.add(dataFig);
	        			
	        			int n=(int) ((int)(tempMs-s_time)/(interval));
	        			for(int j=0;j<n;j++){
	        				s_time+=interval;
	        				dataFig=new DataFig(num,new Timestamp(s_time));
		        			dataFigList.add(dataFig);
	        			}
	        			if((tempMs-s_time)%interval!=0){
	        				num=rs.getInt(1);
	        			}
	        		}
	        		
	         }
			 if(s_time<e_time){
				 s_time+=interval;
				 dataFig=new DataFig(num,new Timestamp(s_time));
     			 dataFigList.add(dataFig);
				 int n=(int) Math.ceil((e_time-s_time)/(interval));
				 for(int j=0;j<n;j++){
					 s_time+=interval;
     				dataFig=new DataFig(num,new Timestamp(s_time));
	        		dataFigList.add(dataFig);
     			}
			 }
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(ps);
			JdbcUtils.close(conn);
		}
		return dataFigList;
	}
	

	public List<Double> getSituationData(String start_time, String end_time,List<Double> weight) {
		List<Integer> netflowList=getNetFlow(start_time, end_time);
		List<Integer> severityList=getSeverity(start_time, end_time);
		List<Integer> alarmList=getAlarmNum(start_time, end_time);
		List<Integer> UDPList=getUDPNum(start_time, end_time);
		List<Integer> TCPList=getTCPNum(start_time, end_time);
		List<Integer> ICMPList=getICMPNum(start_time, end_time);
		List<Double> list=new ArrayList<Double>();
		double result=0;
		int sum,udp,tcp,icmp;
		for(int i=0;i<netflowList.size();i++){
			//System.out.println(netflowList.get(i));netflowList.get(i)*weight.get(0)+
			sum=UDPList.get(i)+TCPList.get(i)+ICMPList.get(i);
			udp=Math.round(UDPList.get(i)*100/sum);
			tcp=Math.round(TCPList.get(i)*100/sum);
			icmp=Math.round(ICMPList.get(i)*100/sum);
			result=severityList.get(i)*weight.get(1)+alarmList.get(i)*weight.get(2)+udp*weight.get(3)+tcp*weight.get(4)+icmp*weight.get(5);
			list.add(result);
		}
		
		return list;
	}

	private List<Integer> getAlarmNum(String start_time, String end_time) {
		String sql="SELECT timestamp,count(*) FROM alert WHERE timestamp >="+"'"+Timestamp.valueOf(start_time)+"'"+"and timestamp<="+"'"+Timestamp.valueOf(end_time)+"'" +"group by timestamp order by timestamp asc";	
		return getList(start_time, end_time, sql);
	}
	
	private List<Integer> getSeverity(String start_time, String end_time) {
		String sql="SELECT timestamp,sum(severity) FROM alert WHERE timestamp >="+"'"+Timestamp.valueOf(start_time)+"'"+"and timestamp<="+"'"+Timestamp.valueOf(end_time)+"'" +"group by timestamp order by timestamp asc";	
		return getList(start_time, end_time, sql);
	}
	
	private List<Integer> getNetFlow(String start_time, String end_time) {
		String sql="SELECT timestamp,sum(ip_len) FROM iphdr WHERE timestamp >="+"'"+Timestamp.valueOf(start_time)+"'"+"and timestamp<="+"'"+Timestamp.valueOf(end_time)+"'" +"group by timestamp order by timestamp asc";	
		return getList(start_time, end_time, sql);
	}
	
	private List<Integer> getUDPNum(String start_time, String end_time) {
		String sql="SELECT timestamp,count(*) FROM iphdr WHERE timestamp >="+"'"+Timestamp.valueOf(start_time)+"'"+"and timestamp<="+"'"+Timestamp.valueOf(end_time)+"' and ip_proto=17 "+"group by timestamp order by timestamp asc";	
		return getList(start_time, end_time, sql);
	}
	
	private List<Integer> getTCPNum(String start_time, String end_time) {
		String sql="SELECT timestamp,count(*) FROM iphdr WHERE timestamp >="+"'"+Timestamp.valueOf(start_time)+"'"+"and timestamp<="+"'"+Timestamp.valueOf(end_time)+"' and ip_proto=6 "+"group by timestamp order by timestamp asc";	
		return getList(start_time, end_time, sql);
	}
	
	private List<Integer> getICMPNum(String start_time, String end_time) {
		String sql="SELECT timestamp,count(*) FROM iphdr WHERE timestamp >="+"'"+Timestamp.valueOf(start_time)+"'"+"and timestamp<="+"'"+Timestamp.valueOf(end_time)+"' and ip_proto=6 "+"group by timestamp order by timestamp asc";	
		return getList(start_time, end_time, sql);
	}

	private List<Integer> getList(String start_time, String end_time, String sql) {
		Connection conn=null;
	    ResultSet rs=null;
	    PreparedStatement ps = null;
	    long s_time=getHaoMiao(start_time);
	    long e_time=getHaoMiao(end_time);

	    List<Integer> list=new ArrayList<Integer>();    
	    try {
	    	conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);	
			rs = ps.executeQuery();
			int tempSeverity=0;
			long interval=5*60*1000;
			 while(rs.next()){
				 long tempMs=getHaoMiao2(rs.getTimestamp(1));

	        		if(tempMs>=s_time && tempMs<s_time+interval){
	        			tempSeverity+=rs.getInt(2);  
	        		}
	        		else if(tempMs>=s_time+interval){
	        			list.add(tempSeverity);
	        			s_time+=interval;
	        			int n=(int) ((int)(tempMs-s_time)/(interval));
	        			for(int j=0;j<n;j++){
	        				list.add(0);
	        				s_time+=interval;
	        			}
	        			if((tempMs-s_time)%interval!=0){
	        				tempSeverity=rs.getInt(2);
	        			}
	        		}
	        		
	         }
			 if(s_time<e_time){
				 list.add(tempSeverity);
				 s_time+=interval;
				 int n=(int) Math.ceil((e_time-s_time)/(interval));
				 for(int j=0;j<n;j++){
     				list.add(0);
     			}
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private long getHaoMiao2(Timestamp timestamp) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String str = dateFormat.format(timestamp);
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  //此处转换为毫秒数
		  long millionSeconds=0;
		try {
			millionSeconds = sdf.parse(str).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return millionSeconds;
	}
	
	private long getHaoMiao(String start_time) {

		Date date=new Date();
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date=sdf.parse(start_time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

}
