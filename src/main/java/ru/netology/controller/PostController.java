//package ru.netology.controller;
//
//import com.google.gson.Gson;
//import ru.netology.exception.NotFoundException;
//import ru.netology.model.Post;
//import ru.netology.service.PostService;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.Reader;
//
//public class PostController {
//    public static final String APPLICATION_JSON = "application/json";
//    private final PostService service;
//    private final Gson gson;
//
//    public PostController(PostService service) {
//        this.service = service;
//        gson = new Gson();
//    }
//
//    public void all(HttpServletResponse response) throws IOException {
//        response.setContentType(APPLICATION_JSON);
//        final var data = service.all();
//        response.getWriter().print(gson.toJson(data));
//    }
//
//    public void getById(long id, HttpServletResponse response) throws IOException {
//        response.setContentType(APPLICATION_JSON);
//        final var data = service.getById(id);
//        response.getWriter().print(gson.toJson(data));
//    }
//
//    public void save(Reader body, HttpServletResponse response) throws IOException {
//        response.setContentType(APPLICATION_JSON);
//        final var post = gson.fromJson(body, Post.class);
//        final var data = service.save(post);
//        response.getWriter().print(gson.toJson(data));
//    }
//
//    public void removeById(long id, HttpServletResponse response) throws IOException {
//        response.setContentType(APPLICATION_JSON);
//        final var data = service.getById(id);
//        service.removeById(id);
//        response.getWriter().print(gson.toJson(data));
//    }
//}
package ru.netology.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import java.util.Collection;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Post> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }
}