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







import net.dao.NetFlowDAO;
import net.domain.DataFig;
import net.domain.NetFlow;
import net.utils.JdbcUtils;
import net.web.page.CriteriaObj;
import net.web.page.Page;

public class NetFlowDAOImpl implements NetFlowDAO {

	@Override
	public Page<NetFlow> getPage(CriteriaObj cb) {
		Page<NetFlow> page = new Page<>(cb.getPageNo());	
		page.setTotalItemNumber(getTotalNetFlowNumber(cb));
		//校验 pageNo 的合法性
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, 15));
		
		return page;
	}

	@Override
	public long getTotalNetFlowNumber(CriteriaObj cb) {

		String sql="SELECT count(*) FROM iphdr WHERE timestamp >="+"'"+Timestamp.valueOf(cb.getStart_time())+"'"+"and timestamp<="+"'"+Timestamp.valueOf(cb.getEnd_time())+"'";
		
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

	/**
	 * MySQL 分页使用 LIMIT, 其中 fromIndex 从 0 开始。 
	 */
	@Override
	public List<NetFlow> getPageList(CriteriaObj cb, int pageSize) {
		
		String sql="SELECT cid, ip_src, ip_dst, ip_ver, ip_hlen, ip_tos, ip_len, ip_ttl, ip_proto, timestamp FROM iphdr WHERE timestamp >="+"'"+Timestamp.valueOf(cb.getStart_time())+"'"+"and timestamp<="+"'"+Timestamp.valueOf(cb.getEnd_time())+"'" +"LIMIT ?, ?";
		
		Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps = null;
        try {
        	conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (cb.getPageNo() - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			 
		    List<NetFlow> netFlowList = new ArrayList<NetFlow>();
		    
			 while(rs.next()){
				 Date d=new Date();
				 d=rs.getTimestamp(10);
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			     Date date= new Date(d.getTime());
				 NetFlow  netFlow = new NetFlow(rs.getInt(1),long2IP(rs.getLong(2)),long2IP(rs.getLong(3)),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),sdf.format(date));
				 netFlowList.add(netFlow); 
	         }  
			 return netFlowList;
			 
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

	@Override
	public List<DataFig> getTotalNetFlowData(String start_time,String end_time) {
		
		String sql="SELECT sum(ip_len),timestamp FROM iphdr WHERE timestamp >="+"'"+Timestamp.valueOf(start_time)+"'"+"and timestamp<="+"'"+Timestamp.valueOf(end_time)+"'" +"group by timestamp order by timestamp asc";		
		Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps = null;
        List<DataFig> dataFigList = new ArrayList<DataFig>();
        try {
        	conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			 while(rs.next()){
	        	
				 DataFig dataFig=new DataFig(rs.getInt(1),rs.getTimestamp(2));
				 dataFigList.add(dataFig);
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
	  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getTotalNetFlowData2(String start_time,String end_time) {
		
		String sql="SELECT sum(ip_len),timestamp FROM iphdr WHERE timestamp >="+"'"+Timestamp.valueOf(start_time)+"'"+"and timestamp<="+"'"+Timestamp.valueOf(end_time)+"'" +"group by timestamp order by timestamp asc";		
		Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps = null;
        List list=new ArrayList();
        long s_time=getHaoMiao(start_time);
		long e_time=getHaoMiao(end_time);
        try {
        	conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			 while(rs.next()){
				long temp=getHaoMiao2(rs.getTimestamp(2));
	        	for(;s_time<temp;s_time+=1000){
	        		list.add(0);
	        	}
	        	s_time+=1000;
	        	list.add(rs.getInt(1));
	         }
			 for(;s_time<=e_time;s_time+=1000){
				 list.add(0);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(ps);
			JdbcUtils.close(conn);
		}
		return list;	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getTotalNetFlowData3(String start_time,String end_time) {
		
		String sql="SELECT ip_dst,sum(ip_len) FROM iphdr WHERE timestamp >="+"'"+Timestamp.valueOf(start_time)+"'"+"and timestamp<="+"'"+Timestamp.valueOf(end_time)+"'"+"group by ip_dst order by sum(ip_len) desc limit 10";
		
		Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps = null;
        List list=new ArrayList();    
        try {
        	conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			 while(rs.next()){
				 List list2=new ArrayList();
				 list2.add(long2IP(rs.getLong(1)));
				 list2.add((rs.getInt(2))*8);  
				 list.add(list2);
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(ps);
			JdbcUtils.close(conn);
		}
        //System.out.println(list.size());
		return list;	
	}
	
	
@SuppressWarnings({ "unchecked", "rawtypes" })
public List getTotalNetFlowData4(String start_time,String end_time) {
		
		String sql="SELECT ip_proto,count(*) FROM iphdr WHERE timestamp >="+"'"+Timestamp.valueOf(start_time)+"'"+"and timestamp<="+"'"+Timestamp.valueOf(end_time)+"'"+"group by ip_proto";
		
		Connection conn=null;
        ResultSet rs=null;
        PreparedStatement ps = null;
        List list=new ArrayList();    
        try {
        	conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			 while(rs.next()){
				List list2=new ArrayList();
				 if(rs.getInt(1)==17){
					 list2.add("UDP");
					 list2.add(rs.getInt(2));
				 }else if(rs.getInt(1)==6)
				 {
					 list2.add("TCP");
					 list2.add(rs.getInt(2));
				 }else if(rs.getInt(1)==1){
					 list2.add("ICMP");
					 list2.add(rs.getInt(2));
				 } 
				 list.add(list2);
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(ps);
			JdbcUtils.close(conn);
		}
        //System.out.println(list.size());
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
