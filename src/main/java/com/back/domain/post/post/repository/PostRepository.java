package com.back.domain.post.post.repository;

import com.back.domain.post.post.dto.Post;
import org.apache.ibatis.annotations.*;

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

    @Insert("""
    <script>
    INSERT INTO post
    set createDate = NOW(),
    modifyDate = NOW(),
    title = #{title},
    content = #{content}
    </script>
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(Post post);

    @Insert("""
    <script>
    INSERT INTO post
    set createDate = NOW(),
    modifyDate = NOW(),
    title = #{title},
    content = #{content}
    </script>
    """)
    void createV2(String title,  String content);

    @Select("""
    SELECT LAST_INSERT_ID()
    """)
    public int getLastInsertId();


    @Delete("""
        DELETE FROM post
        WHERE id = #{id}
    """)
    int deleteById(int id);

    @Update("""
        UPDATE post
        set modifyDate = NOW(),
            title = #{title},
            content = #{content}
        where id = #{id}
    """)
    int update(
            @Param("id") int id,
            @Param("title") String title,
            @Param("content") String content
    );

    // WHERE title LIKE '%${kw}%' 방식도 가능
    @Select("""
        select * 
        from post
        WHERE title LIKE CONCAT('%', #{kw}, '%')
    """)
    List<Post> search(
            @Param("kwType") String kwType,
            @Param("kw") String kw
    );
}
