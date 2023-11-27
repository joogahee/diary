package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dao.CounterDao;
import vo.Counter;

@WebListener
public class MySessionListener implements HttpSessionListener {
	


    public void sessionCreated(HttpSessionEvent se)  {
    	// view에서 application.getAttribute("currentCnt")값을 호출하면 현재 접속자수를 출력가능
         //+1
    	
    	System.out.println("리스너 동작 확인 디버깅!");
    	int c = (Integer)se.getSession().getServletContext().getAttribute("currentCnt");
    	se.getSession().getServletContext().setAttribute("currentCnt", c+1);
    	
    	System.out.println(c + " <-- C");
    	
    	//오늘 날짜 누적 접속자
    	CounterDao counterDao = new CounterDao();
    	int todayCnt = counterDao.selectCounterByToday();
    	System.out.println(todayCnt + "<--오늘 총 접속자");
    	int row = 0;
    	
    	if(todayCnt == 0) {//오늘 첫번째 접속
    		row = counterDao.insertCounter();
    		if(row == 1) {
    			System.out.println("count 성공");
    		}else {
    			System.out.println("count 실패");
    		}
    	}else {
    		row = counterDao.updateCounter();
    		if(row == 1) {
    			System.out.println("count 성공");
    		}else {
    			System.out.println("count 실패");
    		}
    	}
    	
    	se.getSession().getServletContext().setAttribute("todayCnt", todayCnt);
    	
    	//총 누적 접속 수
    	int sumByToday = counterDao.selectCounterSum();
    	se.getSession().getServletContext().setAttribute("sumByToday", sumByToday);
    	
    	 // 추가 디버깅 로그
        System.out.println("현재 접속자 수 : " + se.getSession().getServletContext().getAttribute("currentCnt"));
        System.out.println("총 접속자 수: " + se.getSession().getServletContext().getAttribute("sumByToday"));
        System.out.println("오늘 총 접속자 수: " + se.getSession().getServletContext().getAttribute("todayCnt"));
    }


    public void sessionDestroyed(HttpSessionEvent se)  { 
         //-1
    	int c = (Integer)se.getSession().getServletContext().getAttribute("currentCnt");
    	se.getSession().getServletContext().setAttribute("currentCnt", c-1);
    }
	
}
