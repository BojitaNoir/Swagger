package utez.edu.mx.unidad3.modules.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.unidad3.utils.APIResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public APIResponse findAll(){
        List<Client> list = new ArrayList<>();
        list=clientRepository.findAll();

        return new APIResponse("Operacion exitosa", list,false, HttpStatus.OK);
    }
    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public APIResponse saveClient(Client payload){
        try{
            if(clientRepository.findByEmail(payload.getEmail()).isPresent()){
                return new APIResponse("El usuario ya existe",true,HttpStatus.BAD_REQUEST);
            }
            clientRepository.save(payload);
            return new APIResponse("Operacion Exitosa",false,HttpStatus.CREATED);
        }catch (Exception ex){
            ex.printStackTrace();
            return new APIResponse("Error al crear el cliente",true,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
