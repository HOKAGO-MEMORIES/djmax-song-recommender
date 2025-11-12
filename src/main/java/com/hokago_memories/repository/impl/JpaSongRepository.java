package com.hokago_memories.repository.impl;

import com.hokago_memories.domain.song.Song;
import com.hokago_memories.repository.SongRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class JpaSongRepository implements SongRepository {

    private final EntityManager em;

    public JpaSongRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Song song) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(song);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void saveAll(List<Song> songs) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            songs.forEach(em::persist);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<Song> findAll() {
        return em.createQuery("SELECT s FROM Song s", Song.class)
                .getResultList();
    }

    @Override
    public Optional<Song> findByTitle(int title) {
        Song song = em.find(Song.class, title);
        return Optional.ofNullable(song);
    }

    @Override
    public void clear() {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.createQuery("DELETE FROM Song").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
