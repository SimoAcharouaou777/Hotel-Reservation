package Controller;
import Service.StatistiqueService;
public class StatistiqueController {
    private StatistiqueService statistiqueService;
    public StatistiqueController(){
        this.statistiqueService = new StatistiqueService();
    }
    public void getStatistique(){
        statistiqueService.getStatistique();
    }
}
