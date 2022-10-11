package Service;

import Model.Client;
import Model.Quadbike;
import Repository.QuadbikeRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuadbikeService {

    @Autowired
    private QuadbikeRepository quadbikeRepository;

    public List<Quadbike> getAll() {
        return quadbikeRepository.getAll();
    }

    public Optional<Quadbike> getQuadbike(int id) {
        return quadbikeRepository.getQuadbike(id);
    }

    public Quadbike save(Quadbike quadbike) {
        if (quadbike.getId() == null) {
            return quadbikeRepository.save(quadbike);
        } else {
            Optional<Quadbike> quadbikeEncontrado = getQuadbike(quadbike.getId());
            if (quadbikeEncontrado.isPresent()) {
                return quadbike;
            } else {
                return quadbikeRepository.save(quadbike);
            }

        }
    }

    public Quadbike update(Quadbike quadbike) {
        if (quadbike.getId() != null) {
            Optional<Quadbike> quadbikeEncontrado = getQuadbike(quadbike.getId());
            if (quadbikeEncontrado.isPresent()) {
                if (quadbike.getName() != null) {
                    quadbikeEncontrado.get().setName(quadbike.getName());
                }
                if (quadbike.getBrand() != null) {
                    quadbikeEncontrado.get().setBrand(quadbike.getBrand());
                }
                if (quadbike.getYear() != null) {
                    quadbikeEncontrado.get().setYear(quadbike.getYear());
                }

                if (quadbike.getDescription() != null) {
                    quadbikeEncontrado.get().setDescription(quadbike.getDescription());
                }
                if (quadbike.getCategory() != null) {
                    quadbikeEncontrado.get().setCategory(quadbike.getCategory());
                }
                quadbikeRepository.save(quadbikeEncontrado.get());
                return quadbikeEncontrado.get();
            } else {
                return quadbike;
            }
        } else {
            return quadbike;
        }
    }
    public boolean delete(int id) {
        boolean respuesta = false;
        Optional<Quadbike> quadbikeEncontrado = quadbikeRepository.getQuadbike(id);
        if (quadbikeEncontrado.isPresent()){
            quadbikeRepository.delete(quadbikeEncontrado.get());
    }
        return respuesta;

    }

}
