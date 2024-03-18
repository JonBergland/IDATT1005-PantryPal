package stud.ntnu.idatt1005.pantrypal.registers;

import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Shelf;

import java.util.LinkedHashMap;

public class ShelfRegister extends Register<Shelf> {
    public ShelfRegister() {
        super();
    }

    public LinkedHashMap<String, Shelf> getShelfRegister() {
        return this.getRegister();
    }

    public void addShelf(Shelf shelf) {
        this.addModel(shelf);
    }

    public void removeShelf(Shelf shelf) {
        this.removeModel(shelf);
    }

    public Grocery[] getAllGroceries() {
        return this.getRegister().values().stream()
                .flatMap(shelf -> shelf.getGroceries().values().stream())
                .toArray(Grocery[]::new);
    }
}
