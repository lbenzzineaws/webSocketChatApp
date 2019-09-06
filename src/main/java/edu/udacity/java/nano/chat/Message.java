package edu.udacity.java.nano.chat;


/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
	
	 private String content;
	 private String username;
	 public String type;
	 private int onlineCount;

	    public Message() {}

	    public Message(String text, String  username , String type) {
	        this.content = text;
	        this.username = username;
	        this.type = type;
	        this.onlineCount+= 1;
	        
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }

		public void setUsername(String username) {
			
			this.username = username;
			
		}
		
		public String getUsername() {
			return username;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getOnlineCount() {
			return onlineCount;
		}

		public void setOnlineCount(int onlineCount) {
			this.onlineCount = onlineCount;
		}
		
		
}
