package Service;

import Model.Reservation;
import Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){

        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id){

        return reservationRepository.getReservation(id);
    }

    public Reservation save (Reservation reservation){
        if (reservation.getIdReservation()==null){
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> reservationEncontrado = getReservation(reservation.getIdReservation());
            if(reservationEncontrado.isPresent()){
                return reservation;
            } else {
                return reservationRepository.save(reservation);
            }
        }
    }
    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> reservationEncontrado = getReservation(reservation.getIdReservation());
            if (reservationEncontrado.isPresent()) {
                if (reservation.getStartDate() != null) {
                    reservationEncontrado.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    reservationEncontrado.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    reservationEncontrado.get().setStatus(reservation.getStatus());
                }
                reservationRepository.save(reservationEncontrado.get());
                return reservationEncontrado.get();
            } else {
                return reservation;
            }
        }else{
                return reservation;
            }
    }
    public boolean delete(int id){
        boolean respuesta = false;
        Optional<Reservation> reservationEncontrado = reservationRepository.getReservation(id);
        if(reservationEncontrado.isPresent()){
            reservationRepository.delete(reservationEncontrado.get());
        }
        return respuesta;

    }

}
