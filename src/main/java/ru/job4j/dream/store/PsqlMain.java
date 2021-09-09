package ru.job4j.dream.store;

import ru.job4j.dream.model.Post;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.save(new Post(0, "Java Job"));
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }

        Post post = store.findById(1);
        System.out.println("Post: id = " + post.getId() + ", name = " + post.getName());

        store.save(new Post(1, "New Java Job"));
        post = store.findById(1);
        System.out.println("Post: id = " + post.getId() + ", name = " + post.getName());
    }
}