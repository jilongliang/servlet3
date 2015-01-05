package ivyy.taobao.com.servlet3;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.comet.CometEvent;
import org.apache.catalina.comet.CometProcessor;

/**
 * http://www.txdnet.cn/log/20561367000001.xhtm
 *@Author:jilongliang
 *@Date  :2012-5-21
 *@Project:servlet3.0
 */
@WebServlet(name="ChatRoomServlet",urlPatterns = "/ChatRoomServlet")
@SuppressWarnings("all")
public class ChatRoomServlet extends HttpServlet  implements CometProcessor{
    protected ArrayList<HttpServletResponse> connections = new ArrayList<HttpServletResponse>();
    protected MessageSender messageSender = null;
    
    @Override
      public void init() throws ServletException {
        messageSender = new MessageSender();
        Thread messageSenderThread = new Thread(messageSender, "MessageSender");
        messageSenderThread.setDaemon(true);
        messageSenderThread.start();
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    @Override
    public void event(CometEvent event) throws IOException, ServletException {
        HttpServletRequest request = event.getHttpServletRequest();
        HttpServletResponse response = event.getHttpServletResponse();
        if (event.getEventType() == CometEvent.EventType.BEGIN) {
            log("Begin. sid="+request.getSession(true).getId());
           messageSender.send(request.getParameter("user"), request.getParameter("msg"));
            synchronized(connections) {
                connections.add(response);
            }
        } else if (event.getEventType() == CometEvent.EventType.ERROR) {
            log("Error: sid="+request.getSession(true).getId()+". "+event.getEventSubType().toString());
            synchronized(connections) {
                connections.remove(response);
            }
            event.close();
        } else if (event.getEventType() == CometEvent.EventType.END) {
            log("End. sid="+request.getSession(true).getId());
            synchronized(connections) {
                connections.remove(response);
            }
            event.close();
        } else if (event.getEventType() == CometEvent.EventType.READ) {
            log("Read. sid="+request.getSession(true).getId());
            InputStream is = request.getInputStream();
            byte[] buf = new byte[512];
            do {
                int n = is.read(buf); //can throw an IOException
                if (n > 0) {
                    log("Read " + n + " bytes: " + new String(buf, 0, n)
                            + " for session: " + request.getSession(true).getId());
                } else if (n < 0) {
                    // error(event, request, response);
                    return;
                }
            } while (is.available() > 0);
        }
    }
    
    @Override
    public void destroy() {
        connections.clear();
        messageSender.stop();
        messageSender = null;
    }
    
    public class MessageSender implements Runnable {
        protected boolean running = true;
        protected ArrayList<String> messages = new ArrayList<String>();
        public MessageSender() {
        }
        public void stop() {
            running = false;
        }
        /**
         * Add message for sending.
         */
        public void send(String user, String message) {
            if(user==null || message==null){
                 return;
               }
            synchronized (messages) {
                 messages.add(user.concat(": ").concat(message));
                 messages.notify(); 
            }
        }
    @Override
        public void run() {
            while (running) {
                if (messages.size() == 0) {
                    try {
                        synchronized (messages) {
                            messages.wait();
                        }
                    } catch (InterruptedException e) {
                        // Ignore
                    }
                }
                synchronized (connections) {
                    String[] pendingMessages = null;
                    synchronized (messages) {
                        pendingMessages = messages.toArray(new String[0]);
                        messages.clear();
                    }
                    // Send any pending message on all the open connections
                    for (int i = 0; i < connections.size(); i++) {
                        try {
                            PrintWriter writer = connections.get(i).getWriter();
                            for (int j = 0; j < pendingMessages.length; j++) {
                                writer.println(pendingMessages[j] + "<br/>");
                            }
                            writer.flush();
                        } catch (IOException e) {
                            log("IOExeption sending message", e);
                        }
                    }
                }
            }
        }
    }
}