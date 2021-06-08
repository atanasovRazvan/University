package RESTController;

import Repositories.GameRepository;
import Repositories.RoundRepository;
import javafx.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class RestService {

    private GameRepository gr = new GameRepository();
    private RoundRepository rr = new RoundRepository();

    @GetMapping("/{gameid}")
    public ResponseEntity<?> getWords(@PathVariable("gameid") Integer gameid){
        List<String> list = gr.getWords(gameid);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{gameid}/{player}")
    public ResponseEntity<?> getAnswersAndScores(@PathVariable("gameid") Integer gameid, @PathVariable("player") String player){
        List<Pair<String, Integer>> list = rr.getAnswersAndScores(player, gameid);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
