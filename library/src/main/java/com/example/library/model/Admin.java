package com.example.library.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdmin")
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "username")
    //email
    private String userName;
    @Column(name = "password")
    private String password;

    @Lob
    @Column(columnDefinition =  "MEDIUMBLOB")
    private String image;
    // n-n ( nhieu admin - nhieu roles)
    // fetch = FetchTyoe.Lazy : khi find, select Admin , khong lay doi tuong
    // cascade : khi một bản ghi thay đổi, thì sẽ tự động update các bản ghi tham chiếu
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "admin_roles", joinColumns = @JoinColumn(name = "idAdmin",referencedColumnName = "idAdmin"),
                                    inverseJoinColumns = @JoinColumn(name = "id_Role",referencedColumnName = "id_Role"))
    private Collection<Roles> rolesCollection;


}
