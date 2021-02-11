package cz.cvut.fit.tjv.semestral.business;

import cz.cvut.fit.tjv.semestral.data.entities.*;
import org.springframework.web.multipart.MultipartFile;


public interface BookService extends CrudService<Book, String>{
    void RateUp(String id);
    void RateDown(String id);
//    void updateImg(String id, MultipartFile img);
}
