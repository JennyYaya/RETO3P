package Service;

import Model.Message;
import Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {


    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){

        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id){

        return messageRepository.getMessage(id);
    }

    public Message save (Message message){
        if (message.getIdMessage() == null){
            return messageRepository.save(message);
        } else {
            Optional<Message> messageEncontrado = messageRepository.getMessage(message.getIdMessage());
            if(messageEncontrado.isPresent()){
                return message;
            } else {
                return messageRepository.save(message);
            }
        }
    }
    public Message update(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> messageEncontrado = messageRepository.getMessage(message.getIdMessage());
            if (messageEncontrado.isPresent()) {
                if (message.getMessageText() != null) {
                    messageEncontrado.get().setMessageText(message.getMessageText());
                }
                messageRepository.save(messageEncontrado.get());
                return messageEncontrado.get();
            }else {
                return message;
            }
            }else{
            return message;
        }

    }
    public boolean delete(int id){
        boolean respuesta =false;
            Optional<Message> messageEncontrado = messageRepository.getMessage(id);
            if(messageEncontrado.isPresent()){
                messageRepository.delete(messageEncontrado.get());
            }
        return respuesta;

    }


}
