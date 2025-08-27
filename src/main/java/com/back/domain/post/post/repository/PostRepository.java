package com.back.domain.post.post.repository;

import com.back.domain.post.post.dto.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostRepository {

    @Select("""
    <script>
    SELECT * 
    FROM post
    </script>
    """)
    List<Post> findAll();

    @Select("""
    <script>
    SELECT * 
    FROM post   
    WHERE id = #{id}
    </script>
    """)
    Post findById(int id);
}
