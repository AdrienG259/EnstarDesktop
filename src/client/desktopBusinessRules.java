package client;

import common.Conversation;
import common.User;
import serverFiles.InstantiateSerializable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class desktopBusinessRules {

    private User currentUser;
    private List<Conversation> listConversations;

    public desktopBusinessRules(int currentUserID) throws IOException, ClassNotFoundException {

        File userFile = new File("serverFiles/users/" + currentUserID);
        InstantiateSerializable<User> userInstantiate = new InstantiateSerializable<>(userFile);
        currentUser = userInstantiate.fileToInstance();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<Conversation> getListConversations() throws IOException {
        return listConversations;
    }

}
