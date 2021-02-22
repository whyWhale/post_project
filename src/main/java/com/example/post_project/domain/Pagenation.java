package com.example.post_project.domain;

import com.example.post_project.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagenation {
    private int totalPages;
    private Long totalElements;
    private int currentPage;
    private int currentElements;
    List<Post> pagingList;
}
