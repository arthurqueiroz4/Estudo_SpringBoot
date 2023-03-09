package br.com.springboot.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.backend.model.Usuario;
import br.com.springboot.backend.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        
    	Usuario usuario = new Usuario();
    	usuario.setNome(name);
    	usuarioRepository.save(usuario);
    	return "Hello " + name + "!";
    }
    */
    @GetMapping(value="/listartodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listarUsuario(){
	   List<Usuario> usuario = usuarioRepository.findAll();
	   
	   return new ResponseEntity<List<Usuario>>(usuario,HttpStatus.OK);
   }
    
    @PostMapping(value="salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    @PutMapping(value="atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	if (usuario.getId()== null) {
    		return new ResponseEntity<String>("ID não foi informado", HttpStatus.OK);
    	}
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @DeleteMapping(value="delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam(name="id") Long id){
    	System.out.println(1);
    	usuarioRepository.deleteById(id);
    	return new ResponseEntity<String>("User deletado", HttpStatus.OK);
    }
    
    @GetMapping(value="buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name ="id") Long id){
    	Usuario user = usuarioRepository.findById(id).get();
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @GetMapping(value="buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name ="name") String name){
    	List<Usuario> user = usuarioRepository.buscarPorNome(name.trim().toLowerCase());
    	return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);
    }
}
