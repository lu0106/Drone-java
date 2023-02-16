package farm.model;

import java.time.LocalDate;

public class Crop extends FarmItem{
    private String typeOfCrop;
    private LocalDate dateSowed, dateHarvested;

    

    public Crop() {
    	this("",LocalDate.now(),LocalDate.now());
    }
    
    public Crop(String typeOfCrop) {
    	this(typeOfCrop,LocalDate.now(),LocalDate.now());
    }
    
    public Crop(String typeOfCrop, LocalDate dateSowed, LocalDate dateHarvested) {
        this.typeOfCrop = typeOfCrop;
        this.dateSowed = dateSowed;
        this.dateHarvested = dateHarvested;
    }
    
    public String getTypeOfCrop() {
        return typeOfCrop;
    }

    public void setTypeOfCrop(String typeOfCrop) {
        this.typeOfCrop = typeOfCrop;
    }

    public LocalDate getDateSowed() {
        return dateSowed;
    }

    public void setDateSowed(LocalDate dateSowed) {
        this.dateSowed = dateSowed;
    }

    public LocalDate getDateHarvested() {
        return dateHarvested;
    }

    public void setDateHarvested(LocalDate dateHarvested) {
        this.dateHarvested = dateHarvested;
    }
}
