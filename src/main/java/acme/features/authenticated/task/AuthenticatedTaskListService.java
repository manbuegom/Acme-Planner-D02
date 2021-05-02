package acme.features.authenticated.task;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedTaskListService implements AbstractListService<Authenticated, Task>{
    
    @Autowired
    protected AuthenticatedTaskRepository repository;
    
    @Override
    public boolean authorise(final Request<Task> request) {

        assert request != null;
        
        return true;
    }

    @Override
    public void unbind(final Request<Task> request, final Task entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model!= null;
        
        request.unbind(entity, model,  "start", "end", "title", "text","link", "workLoad");
    }

    @Override
    public Collection<Task> findMany(final Request<Task> request) {
        assert request != null;

        final Collection<Task> result;
                
        final Date date = Date.valueOf(LocalDate.now());
        
        //ordena los resultados de mayor a menor carga de workload, si se quiere al reves añadir el .reversed() en el comparator
        result = this.repository.findPublicTasks(date).stream().sorted(Comparator.comparing(Task::getWorkLoad)).collect(Collectors.toList());
        
        return result;
    }

}