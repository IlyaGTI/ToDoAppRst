package com.example.todoapprst.repository;

import com.example.todoapprst.entity.Folder;
import com.example.todoapprst.entity.User;
import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    Set<Folder> findAllByUser(User user);

    @Query("select f from Folder as f where f.nameFolder=:name")
    Folder findFolderByNameFolder(@Param("name") String name);

    @Query("select f from Folder as f where f.nameFolder =: nameFolder and f.user =: user")
    Folder findRootFolder(@Param("nameFolder")  String nameFolder, @Param("name") User user);

    Folder findFolderByNameFolderAndUser(String folder, User user);
}
