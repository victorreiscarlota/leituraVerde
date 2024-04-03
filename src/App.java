import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Acomodacao implements Cloneable {
    private int roomId;
    private int hostId;
    private String roomType;
    private String country;
    private String city;
    private String neighbourhood;
    private int reviews;
    private double overallSatisfaction;
    private int accommodates;
    private double bedrooms;
    private double price;
    private String propertyType;

    public Acomodacao(int roomId, int hostId, String roomType, String country, String city, String neighbourhood,
            int reviews, double overallSatisfaction, int accommodates, double bedrooms, double price,
            String propertyType) {
        this.roomId = roomId;
        this.hostId = hostId;
        this.roomType = roomType;
        this.country = country;
        this.city = city;
        this.neighbourhood = neighbourhood;
        this.reviews = reviews;
        this.overallSatisfaction = overallSatisfaction;
        this.accommodates = accommodates;
        this.bedrooms = bedrooms;
        this.price = price;
        this.propertyType = propertyType;
    }

    
    public Acomodacao(Acomodacao other) {
        this.roomId = other.roomId;
        this.hostId = other.hostId;
        this.roomType = other.roomType;
        this.country = other.country;
        this.city = other.city;
        this.neighbourhood = other.neighbourhood;
        this.reviews = other.reviews;
        this.overallSatisfaction = other.overallSatisfaction;
        this.accommodates = other.accommodates;
        this.bedrooms = other.bedrooms;
        this.price = other.price;
        this.propertyType = other.propertyType;
    }

    
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public double getOverallSatisfaction() {
        return overallSatisfaction;
    }

    public void setOverallSatisfaction(double overallSatisfaction) {
        this.overallSatisfaction = overallSatisfaction;
    }

    public int getAccommodates() {
        return accommodates;
    }

    public void setAccommodates(int accommodates) {
        this.accommodates = accommodates;
    }

    public double getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(double bedrooms) {
        this.bedrooms = bedrooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    
    public Acomodacao clone() {
        return new Acomodacao(this);
    }


    public void imprimir() {
        System.out.println("[roomId " + roomId + " ## hostId " + hostId + " ## roomType " + roomType +
                " ## country " + country + " ## city " + city + " ## neighbourhood " + neighbourhood +
                " ## reviews " + reviews + " ## overallSatisfaction " + overallSatisfaction +
                " ## accommodates " + accommodates + " ## bedrooms " + bedrooms + " ## price " + price +
                " ## propertyType " + propertyType + "]");
    }
}

public class App {
    public static void main(String[] args) {
        
        Acomodacao[] acomodacoes = lerDadosAcomodacoes("/tmp/dados_airbnb.txt");
        
        processarEntrada(acomodacoes);
    }

    
    public static Acomodacao[] lerDadosAcomodacoes(String fileName) {
        File file = new File(fileName);
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                br.readLine();
                int numAcomodacoes = 0;
                while (br.readLine() != null) {
                    numAcomodacoes++;
                }

                Acomodacao[] acomodacoes = new Acomodacao[numAcomodacoes];
                br.close();
                try (BufferedReader br2 = new BufferedReader(new FileReader(file))) {
                    br2.readLine(); 
                    int index = 0;
                    String line;
                    while ((line = br2.readLine()) != null) {
                        String[] dados = line.split("\t");
                        acomodacoes[index++] = new Acomodacao(Integer.parseInt(dados[0]),
                                Integer.parseInt(dados[1]), dados[2], dados[3], dados[4], dados[5],
                                Integer.parseInt(dados[6]), Double.parseDouble(dados[7]),
                                Integer.parseInt(dados[8]), Double.parseDouble(dados[9]),
                                Double.parseDouble(dados[10]), dados[11]);
                    }
                    return acomodacoes;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void processarEntrada(Acomodacao[] acomodacoes) {
        try (BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in))) {
            String id;
            do {
                id = br.readLine();
                if (!id.equals("FIM")) {
                    int roomId = Integer.parseInt(id);
                    for (Acomodacao acomodacao : acomodacoes) {
                        if (acomodacao.getRoomId() == roomId) {
                            acomodacao.imprimir();
                            break;
                        }
                    }
                }
            } while (!id.equals("FIM"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
