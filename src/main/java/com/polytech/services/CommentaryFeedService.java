package com.polytech.services;

import com.polytech.persistence.CommentaryRepository;

import java.util.List;

public class CommentaryFeedService {
    private CommentaryRepository commentaryRepository;

    public CommentaryFeedService(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    public List<Commentary> fetchAll(int id_Note) {
        return commentaryRepository.findAll(id_Note);
    }
}
