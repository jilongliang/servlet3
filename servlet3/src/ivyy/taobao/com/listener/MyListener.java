package ivyy.taobao.com.listener;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

/**
 *@Author:jilongliang
 *@Date  :2012-5-21
 *@Project:servlet3.0
 */
public class MyListener implements AsyncListener{

	@Override
	public void onComplete(AsyncEvent asyncevent) throws IOException {
		System.out.println(asyncevent.getAsyncContext());
	}

	@Override
	public void onError(AsyncEvent asyncevent) throws IOException {
		
	}

	@Override
	public void onStartAsync(AsyncEvent asyncevent) throws IOException {
		
	}

	@Override
	public void onTimeout(AsyncEvent asyncevent) throws IOException {
		
	}

}
