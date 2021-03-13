package common;

import java.util.List;

public class ConversationGroupe extends Conversation implements IConversation {

    public ConversationGroupe(String nomGroupe, List<User> members) {
        super(nomGroupe, members);
    }

    //possibilité d'ajouter un membre add_member
    //udpate_conv pour récup les derniers messages
}
