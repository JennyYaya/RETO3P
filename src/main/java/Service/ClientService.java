package Service;

import Model.Client;
import Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id) {
        return clientRepository.getClient(id);
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> clientEncontrado = getClient(client.getIdClient());
            if (clientEncontrado.isPresent()) {
                return client;
            } else {
                return clientRepository.save(client);
            }

        }
    }

    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> clientEncontrado = getClient(client.getIdClient());
            if (clientEncontrado.isPresent()) {
                if (client.getName() != null) {
                    clientEncontrado.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    clientEncontrado.get().setAge(client.getAge());
                }
                if (client.getPassword() != null) {
                    clientEncontrado.get().setPassword(client.getPassword());
                }
                clientRepository.save(clientEncontrado.get());
                return clientEncontrado.get();
            } else {
                return client;
            }

        } else {
            return client;
        }
    }
        public boolean delete(int id){
            boolean respuesta = false;
            Optional<Client> clientEncontrado = clientRepository.getClient(id);
            if (clientEncontrado.isPresent()) {
                clientRepository.delete(clientEncontrado.get());
            }
            return respuesta;

        }
    }

