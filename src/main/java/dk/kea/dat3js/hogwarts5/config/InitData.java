package dk.kea.dat3js.hogwarts5.config;

import dk.kea.dat3js.hogwarts5.house.HouseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import dk.kea.dat3js.hogwarts5.house.House;

@Component
public class InitData implements CommandLineRunner {

  private final HouseRepository houseRepository;

  public InitData(HouseRepository houseRepository) {
    this.houseRepository = houseRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    createHouses();
  }

  private void createHouses() {
     House gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[] {"red", "gold"});
     House slytherin = new House("Slytherin", "Salazar Slytherin", new String[] {"green", "silver"});
     House hufflepuff = new House("Hufflepuff", "Helga Hufflepuff", new String[] {"yellow", "black"});
     House ravenclaw = new House("Ravenclaw", "Rowena Ravenclaw", new String[] {"blue", "bronze"});

     houseRepository.save(gryffindor);
      houseRepository.save(slytherin);
      houseRepository.save(hufflepuff);
      houseRepository.save(ravenclaw);


  }
}
