package web;

import core.GameOfLife;
import core.Grid;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(origins = "*")
@RestController
public class GameOfLifeController {

    @RequestMapping(method=POST, path = "/grid")
    public GridDto getNextState(@RequestBody GridDto grid) throws Exception {

        Grid currentState = mapToCoreObj(grid);

        Grid nextState = GameOfLife.nextState(currentState);

        return mapToDto(nextState);
    }

    @RequestMapping("/check")
    public String check() {
        return "Healthy as a goat!";
    }

    private GridDto mapToDto(Grid grid) {
        return new GridDto(grid.getGrid());
    }

    private Grid mapToCoreObj(GridDto dto) {
        return new Grid(dto.getGrid());
    }
}
