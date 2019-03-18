package bohdan.papizhanskiy.laptops.service;

import bohdan.papizhanskiy.laptops.dto.request.GraphicCardFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.GraphicCardRequest;
import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.GraphicCardResponse;
import bohdan.papizhanskiy.laptops.dto.response.MakeResponse;
import bohdan.papizhanskiy.laptops.entity.GraphicCard;
import bohdan.papizhanskiy.laptops.entity.Make;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.repository.GraphicCardRepository;
import bohdan.papizhanskiy.laptops.specification.GraphicCardSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GraphicCardService {

    @Autowired
    private GraphicCardRepository graphicCardRepository;

    public GraphicCardResponse save(GraphicCardRequest graphicCardRequest) {

        return new GraphicCardResponse(graphicCardRequestToGraphicCard(null, graphicCardRequest));

    }

    public GraphicCardResponse update(GraphicCardRequest graphicCardRequest, Long id) throws WrongInputException {
        return new GraphicCardResponse(graphicCardRequestToGraphicCard(findOne(id), graphicCardRequest));
    }

    private GraphicCard graphicCardRequestToGraphicCard(GraphicCard graphicCard, GraphicCardRequest graphicCardRequest) {
        if (graphicCard == null) {
            graphicCard = new GraphicCard();
        }
        graphicCard.setName(graphicCardRequest.getName());
        graphicCard.setModel(graphicCardRequest.getModel());
        graphicCard.setTypeOfGraphicCard(graphicCardRequest.getTypeOfGraphicCard());
        graphicCard.setTypeOfMemory(graphicCardRequest.getTypeOfMemory());
        graphicCard.setVolumeOfMemory(graphicCardRequest.getVolumeOfMemory());


        return graphicCardRepository.save(graphicCard);
    }


    public List<GraphicCardResponse> findAll() {
        return graphicCardRepository.findAll().stream().map(GraphicCardResponse::new).collect(Collectors.toList());
    }

    public GraphicCard findOne(Long id) throws WrongInputException {
        return graphicCardRepository.findById(id).orElseThrow(() -> new WrongInputException());
    }

    public void delete(Long id) throws WrongInputException {
        graphicCardRepository.delete(findOne(id));
    }

    public DataResponse<GraphicCardResponse> findAll(PaginationRequest paginationRequest) {
        Page<GraphicCard> all = graphicCardRepository.findAll(paginationRequest.mapToPageRequest());
        return new DataResponse<>(all.get().map(GraphicCardResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }

    public DataResponse<GraphicCardResponse> findByFilter(GraphicCardFilterRequest graphicCardFilterRequest){
      Page<GraphicCard> page = graphicCardRepository.findAll( new GraphicCardSpecification(graphicCardFilterRequest),graphicCardFilterRequest.getPagination().mapToPageRequest());

      return new DataResponse<>(page.get().map(GraphicCardResponse::new ).collect(Collectors.toList()), page.getTotalPages(),page.getTotalElements());

    }


}
