package havan.blog.demo.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotiServiceImp implements NotiService{
	//Store info and error msg in List<NotiMessage> in the HTTP session
	//The HTTP session is a special place where you can store objects (key -> value) and they persist for long time. HTTP session objects survive request redirections and may be accessed long time later after they are created. The notification messages will be displayed later in the site header (in layout.html).
	public static final String 	NOTIFY_MSG_SESSION_KEY="siteNotificationMessages";
	
	@Autowired
	private HttpSession httpSession;

	@Override
	public void addInfoMessage(String msg) {
		// TODO Auto-generated method stub
		addNotificationMessage(NotificationMessageType.INFO,msg);
	}

	@Override
	public void AddErrorMessage(String msg) {
		// TODO Auto-generated method stub
		addNotificationMessage(NotificationMessageType.ERROR,msg);
	}
	
	private void addNotificationMessage(NotificationMessageType type, String msg){
		@SuppressWarnings("unchecked")
		List<NotificationMessage> notifyMess=(List<NotificationMessage>)
				httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);	
		if (notifyMess==null) {
			notifyMess= new ArrayList<NotificationMessage>();
		}
		notifyMess.add(new NotificationMessage(type,msg));
		httpSession.setAttribute(NOTIFY_MSG_SESSION_KEY, notifyMess);
	}
	
	public enum NotificationMessageType{
		INFO,
		ERROR
	}
	
	public class NotificationMessage{
		NotificationMessageType type;
		String text;
		
		public NotificationMessage(NotificationMessageType type, String text) {
			// TODO Auto-generated constructor stub
			this.type=type;
			this.text=text;
		}

		public NotificationMessageType getType() {
			return type;
		}

		public String getText() {
			return text;
		}
				
	}
	
}
