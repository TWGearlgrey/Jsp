package sub2;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/welcome.do", "/greeting.do"}) // welcome and greeting.do에 대해서만 filter
public class FilterD implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 필터 처리될 로직 수행
		System.out.println("FilterD doFilter()...");
		
		// 다음 필터 호출, 필터 없으면 최종 자원 요청
		chain.doFilter(request, response);
	}
}