package org.kh.Controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TestServlet
 */
@WebServlet(name = "Test", urlPatterns = { "/test" })
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("파파고 호출");
		request.setCharacterEncoding("utf-8");
		String data = request.getParameter("data");
		
		System.out.println("사용자가 보낸 값 : "+ data);
		String json = TestPaPaGo(data);
		
		System.out.println("파파고에서 응답한 값 : "+json);
		response.setContentType("text/html; charset = utf-8");
		response.getWriter().println(json);
		
		
	}
	public String TestPaPaGo(String srcData){
		String clientId = "FejHOK6_PLiJm5Nln89g";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "J36lCSy1MS";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode(srcData, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=ko&target=en&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
//            System.out.println(response.toString()); //3중 JSON
            
//            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(response.toString()); // JSON 파싱
//            
//            JSONObject json = (JSONObject)obj;
//            Object obj2 = parser.parse(json.get("message").toString());
//            
//            Object obj3 = parser.parse(((JSONObject)obj2).get("result").toString());
//            
//            JSONObject result = (JSONObject)obj3;
            
//            System.out.println(result.get("translatedText")); //번역문 뽑기
            
            return response.toString();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
