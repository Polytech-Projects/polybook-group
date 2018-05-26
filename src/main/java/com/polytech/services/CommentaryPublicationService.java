package com.polytech.services;

import com.polytech.persistence.CommentaryRepository;

public class CommentaryPublicationService {
    private CommentaryRepository commentaryRepository;

    public CommentaryPublicationService(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    public void post(int id_Note, String content, String userName) {
        commentaryRepository.save(new Commentary(content, userName, id_Note), userName);
    }
}
