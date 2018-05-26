package com.polytech.persistence;

import com.polytech.services.Commentary;
import com.polytech.services.Note;

import java.util.List;

public interface CommentaryRepository {
        void save(Commentary commentary, String userName);

        List<Commentary> findAll(int id_Note);
}