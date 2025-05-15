package org.unrealfff.io;

import org.unrealfff.data.unit.*;

import java.io.InputStream;
import java.util.*;

public class Handler {
    /**
     * Handler is responsible for input/output
     */
    private Scanner scanner = new Scanner(System.in);
    private Boolean finished = false;
    private Router router;
    private RouteFactory routeFactory;
    private boolean file = false;
    private ArrayDeque<String> files = new ArrayDeque<>();
    private int recursion = 0;

    public Handler() {
       // this.checkArgs(args);
        this.router = new Router(this, System.getenv("FILEPATH"));
        this.routeFactory = new RouteFactory(this.router.getMaxId());
    }

    private void checkArgs(String[] args) {
        if(args.length != 1) {
            throw new IllegalArgumentException("takes 1 argument - filename");
        }
    }

    public void over() {
        this.finished = true;
    }

    public Route getRoute() {
        String name;
        while (true){
            System.out.print("enter route name\n>");
            name = this.scanner.nextLine().trim();
            if(!name.isEmpty()) {
                break;
            }
            this.incorrectIn();
        }
        System.out.println("building coordinates");
        Coordinates coordinates = this.getCoordinates();
        Location from = this.getLocation();
        Location to;
        while (true) {
            to = this.getLocation();
            if (to != null) {
                break;
            }
            this.incorrectIn();
        }
        Float distance;
        while (true){
            System.out.print("enter route distance\n>");
            String line = this.scanner.nextLine().trim();
            System.out.println(line);
            if(!line.isEmpty()) {
                try {
                    distance = Float.parseFloat(line);
                    if(distance > 1.0) {
                        break;
                    }
                    else{
                        this.incorrectIn();
                    }
                }
                catch (NumberFormatException e) {
                    this.incorrectIn();
                }
            }
            else {
                distance = null;
                break;
            }
        }
        return this.routeFactory.build(name, coordinates, from, to, distance);
    }

    public void work() {
        while (!this.finished) {
            System.out.print(">");
            if(this.file  && !this.scanner.hasNext()) {
                this.setScanner(System.in);
            }
            String[] input = this.scanner.nextLine().split(" ");
            ArrayList<String> ArrayArgs = new ArrayList<>();
            for (int i = 1; i < input.length; i++) {
                ArrayArgs.add(input[i]);
            }
            String[] args = new String[ArrayArgs.size()];
            args = ArrayArgs.toArray(args);
            Response response;
            response = this.router.route(new Request(input[0], args));
            if (!response.text().isEmpty()) {
                System.out.println(response.text());
            }
        }
    }

    public Coordinates getCoordinates() {
        Long x;
        while (true) {
            System.out.print("enter coordinates x\n>");
            String line = this.scanner.nextLine().trim();
            if (!line.isEmpty()) {
                try {
                    x = Long.parseLong(line);
                    if (x <= 500) {
                        break;
                    }
                }
                catch (NumberFormatException e) {}
            }
            this.incorrectIn();
        }
        Long y;
        while (true) {
            System.out.print("enter coordinates y\n>");
            String line = this.scanner.nextLine().trim();
            if (!line.isEmpty()) {
                try {
                    y = Long.parseLong(line);
                    break;
                }
                catch (NumberFormatException e) {}
            }
            this.incorrectIn();
        }
        return new Coordinates(x, y);
    }
    public Location getLocation() {
        System.out.println("building location");
        Integer x;
        while(true) {
            System.out.print("enter location x\n>");
            String line = this.scanner.nextLine().trim();
            if (line.isEmpty()) {
                return null;
            }
            try {
                x = Integer.parseInt(line);
                break;
            }
            catch (NumberFormatException e) {}
            this.incorrectIn();
        }
        double y;
        while(true) {
            System.out.print("enter location y\n>");
            String line = this.scanner.nextLine().trim();
            if (!line.isEmpty()) {
                try {
                    y = Double.parseDouble(line);
                    break;
                }
                catch (NumberFormatException e) {}
            }
            this.incorrectIn();
        }
        Double z;
        while(true) {
            System.out.print("enter location z\n>");
            String line = this.scanner.nextLine().trim();
            if (!line.isEmpty()) {
                try {
                    z = Double.parseDouble(line);
                    break;
                }
                catch (NumberFormatException e) {}
            }
            this.incorrectIn();
        }
        String name;
        while (true){
            System.out.print("enter location name\n>");
            name = this.scanner.nextLine().trim();
            if(!name.isEmpty()) {
                break;
            }
            this.incorrectIn();
        }
        return new Location(x, y, z, name);
    }
    private void incorrectIn() {
        System.out.println("your input is incorrect - please try again");
    }

    public void setScanner(InputStream io) {
        if(this.files.isEmpty()) {
            this.recursion = 0;
            this.scanner = new Scanner(io);
            this.file = false;
        }
        else{
            this.recursion--;
            String help = this.files.pop();
            this.scanner = new Scanner(help);
        }
    }

    public void setScanner(String io) {
        this.recursion++;
        if(!this.file) {
            this.file = true;
        }
        else {
            String restOfTheFile = "";
            while(this.scanner.hasNext()) {
                restOfTheFile += scanner.nextLine() + "\n";
            }
            if (!restOfTheFile.isEmpty()) {
                this.files.push(restOfTheFile);
            }
            else{
                this.recursion--;
            }
        }
        this.scanner = new Scanner(io);
        if(this.recursion >= 256) {
            this.scanner = new Scanner(System.in);
            System.out.println("maximum reqursion depth reached, entering interactive mode");
        }
    }
}
