package tutorial;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private MongoClient mongoClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MongoDatabase database = mongoClient.getDatabase("example");
        MongoCollection<Document> collection = database.getCollection("User");

        Document document = collection.find(Filters.eq("username",username)).first();

        if(document != null) {
            String password = document.getString("password");
            List<String> authorities = (List<String>) document.get("authorities");

            MongoUserDetails mongoUserDetails = new MongoUserDetails(username,password,authorities.toArray(new String[authorities.size()]));

            return mongoUserDetails;
        }
        else {
            System.out.println("Username not found!!!");
            throw new  UsernameNotFoundException("Username not found!!!");
        }
    }
}
