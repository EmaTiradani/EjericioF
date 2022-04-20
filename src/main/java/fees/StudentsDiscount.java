package fees;

public class StudentsDiscount {
    public float applyDiscount(int fee, int hours, int minutes){
        //10% de descuento a estudiantes para las fracciones de 1 hora, o un 15% entre las 8am y 10am para las fracciones de 15mins
        if(hours == 1){
            return fee*0.9f;
        }
        return 0.5f;
    }
}
