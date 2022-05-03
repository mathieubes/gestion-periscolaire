package api.models;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <Parent,UUID> {   /*  j'utilise le type du user id*/
    

List <Parent> findByFirstname(String fullName); /** ici nous allons retourner tous les users avec le nom */

List<Parent> findByLastname (String lastname);

}

/*public Parent addParent (UserPostDTO userPostDTO);

// Je commit sur la branche dev 
// je supprime la branche jpa sur le local
// à partir de dev je crée une branche jpa qui recup le contenu de dev
// supprimer la branche dev local
// fectch ce qu'il y a sur le remote
// repartir sur JPA et ensuite rebase sur dev

}
*/