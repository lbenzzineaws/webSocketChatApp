package edu.udacity.java.nano.chat;



import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;


/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
	
	//private static Logger logger = Logger.(WebSocketChatServer.class);
	private final static Logger logger = Logger.getLogger(WebSocketChatServer.class.getName());
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
	private Session session;
    
    
    
    private static void sendMessageToAll(String msg) throws IOException {
        //TODO: add send message method.
    	//Iterator osIterator = onlineSessions.entrySet().iterator(); 
    	
    	onlineSessions.forEach((id, session) -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    	
    



    /**
     * Open connection, 1) add session, 2) add user.
     * @throws IOException 
     * @throws EncodeException 
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        //TODO: add on open connection.
    	this.session = session;
       
    	logger.info("Open session:" + session.getId());
    	onlineSessions.put(session.getId(), session);
    	
        sendMessageToAll(Message.jsonStr(Message.ENTER, "", "", Integer.toString(onlineSessions.size())));
        }


    /**
     * Send message, 1) get username and session, 2) send message to all.
     * @throws IOException 
     * @throws EncodeException 
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) throws IOException {
        //TODO: add send message.
    	logger.info("Message received: " + jsonStr);
    	
    	//How to get username and from which object ???
    	
    	Message message = JSON.parseObject(jsonStr, Message.class);
        sendMessageToAll(Message.jsonStr(Message.SPEAK, message.getUsername(), message.getMsg(), Integer.toString(onlineSessions.size())));
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     * @throws IOException 
     * @throws EncodeException 
     */
    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        //TODO: add close connection.
    	//onlineSessions.remove(session.getId(), session);
    	onlineSessions.remove(session.getId());
        sendMessageToAll(Message.jsonStr(  Message.QUIT,"", "",Integer.toString(onlineSessions.size())));
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
    
 

}
