package ru.job4j.dream.store;

import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class PsqlStoreTest {

    @Test
    public void whenCreatePost() {
        Store store = PsqlStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        Post postInDb = store.findPostById(post.getId());

        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePost() {
        Store store = PsqlStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        post.setName("Update Java Job");
        store.save(post);
        Post postInDb = store.findPostById(post.getId());

        assertThat(postInDb.getName(), is("Update Java Job"));
    }

    @Test
    public void whenCreateCandidate() {
        Store store = PsqlStore.instOf();
        Candidate candidate = new Candidate(0, "New Candidate");
        store.save(candidate);
        Candidate candidateInDb = store.findCandidateById(candidate.getId());

        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
        Store store = PsqlStore.instOf();
        Candidate candidate = new Candidate(0, "New Candidate");
        store.save(candidate);
        candidate.setName("Update New Candidate");
        store.save(candidate);
        Candidate candidateInDb = store.findCandidateById(candidate.getId());

        assertThat(candidateInDb.getName(), is("Update New Candidate"));
    }

    @Test
    public void whenDeleteCandidate() {
        Store store = PsqlStore.instOf();
        Candidate candidate = new Candidate(0, "Java Junior");
        store.save(candidate);
        Candidate candidateInDb = store.findCandidateById(candidate.getId());
        store.deleteCandidateByID(candidateInDb.getId());

        assertThat(store.findCandidateById(candidateInDb.getId()), is(nullValue()));
    }

    @Test
    public void whenCreateUser() {
        Store store = PsqlStore.instOf();
        User user = new User(0, "New User", "test@email.com", "password");
        store.save(user);
        User userInDb = store.findUserByEmail("test@email.com");

        assertThat(user.getName(), is(userInDb.getName()));
    }

    @Test
    public void whenFindUserByEmail() {
        Store store = PsqlStore.instOf();
        User user = new User(0, "User", "user@email.com", "password");
        store.save(user);
        User userInDb = store.findUserByEmail(user.getEmail());

        assertThat(userInDb.getEmail(), is(user.getEmail()));
    }
}