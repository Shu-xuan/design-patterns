package com.hhy.iterator;

import java.io.*;
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

    private final int batchSize;

    public UserFile(File file) {
        this.file = file;
        this.batchSize = 10;
    }

    public UserFile(File file, int batchSize) {
        this.file = file;
        this.batchSize = batchSize;
    }

    @Override
    public Iterator<User> iterator() {
        return new UserFileIte();
    }

    private class UserFileIte implements Iterator<User> {
        private BufferedReader reader;
        private int cursor = 0;
        private List<User> userList = new ArrayList<>();

        public UserFileIte() {
            try {
                reader = Files.newBufferedReader(file.toPath());
                loadBatch();
//                loadUsersFromFile(user -> userList.add(user));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void loadBatch() throws IOException {
            userList.clear();
            cursor = 0;
            String line;
            while (userList.size() < batchSize && (line = reader.readLine()) != null && !line.equals("") && line.length() != 0) {
                System.out.println(line);
                String[] split = line.substring(1, line.length() - 1).split(",");
                userList.add(new User(split[0], Integer.parseInt(split[1])));
            }
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
            if (cursor < userList.size()) {
                return true;
            }
            try {
                if (reader.ready()) {
                    loadBatch();
                    return !userList.isEmpty();
                }
                return false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public User next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return userList.get(cursor++);
        }
//        @Override
//        public boolean hasNext() {
//            return cursor != userList.size();
//        }

//        @Override
//        public User next() {
//            if (cursor >= userList.size()) {
//                throw new NoSuchElementException();
//            }
//            return userList.get(cursor++);
//        }
    }

}
