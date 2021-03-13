package common;

import java.util.List;

public class ConversationSimple extends Conversation implements IConversation {

    public ConversationSimple(String nomGroupe, List<User> members) {
        super(nomGroupe, members);
    }
}
