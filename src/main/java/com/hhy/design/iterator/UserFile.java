package com.hhy.design.iterator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class UserFile implements Iterable<User>{
    private final File file;

    public UserFile(File file) {
        this.file = file;
    }

    @Override
    public Iterator<User> iterator() {
        return new UserFileIte();
    }

    private class UserFileIte implements Iterator<User> {
        int cursor = 0;
        List<User> userList = new ArrayList<>();
        {
            loadUsersFromFile(user -> userList.add(user));
        }

        private void loadUsersFromFile(Consumer<User> userConsumer) {
            try {
                Files.readAllLines(file.toPath()).stream()
                        .map(line -> {
                            String[] split = line.substring(1, line.length() - 1).split(",");
                            return new User(split[0], Integer.parseInt(split[1]));
                        })
                        .forEach(userConsumer::accept);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean hasNext() {
            return cursor != userList.size();
        }

        @Override
        public User next() {
            if (cursor >= userList.size()) {
                throw new NoSuchElementException();
            }
            return userList.get(cursor++);
        }
    }

}
