package org.xproce.gestiondereservation_hotel.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.xproce.gestiondereservation_hotel.Dao.entities.Room;
import org.xproce.gestiondereservation_hotel.service.RoomService;

import java.util.List;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/saveroom")
    public String ajouterRoom(Model model,
                              @Valid Room room,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterroom";
        }
        roomService.addRoom(room);
        return "redirect:/listroom";
    }

    @GetMapping("/detailsroom")
    public String details(Model model,
                          @RequestParam(name = "id") Integer id) {
        Room room = roomService.getRoomById(id);
        if (room != null) {
            model.addAttribute("RoomWithDetails", room);
            return "detailroom";
        } else {
            return "error";
        }
    }

    @GetMapping("/deleteroom")
    public String deleteRoom(@RequestParam(name = "id") Integer id) {
        if (roomService.deleteRoomById(id)) {
            return "redirect:/listroom";
        } else {
            return "error";
        }
    }

    @PostMapping("/ajouterroom")
    public String ajouterRoomAction(Model model,
                                    @ModelAttribute("room") @Valid Room room,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterroom";
        }
        roomService.addRoom(room);
        return "redirect:/listroom";
    }

    @GetMapping("/ajouterroom")
    public String showAjouterRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "ajouterroom";
    }

    @GetMapping("/listroom")
    public String listRoom(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "6") int size,
                           @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Room> roomPage = roomService.searchRoom(keyword, page, size);
        model.addAttribute("listRoom", roomPage.getContent());
        model.addAttribute("pages", new int[roomPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "indexlayoutroom";
    }

    @GetMapping("/editroom")
    public String editRoomAction(Model model, @RequestParam(name = "id") Integer id) {
        Room room = roomService.getRoomById(id);

        if (room != null) {
            model.addAttribute("RoomToBeUpdated", room);
            return "updateroom";
        } else {
            return "error";
        }
    }
    @PostMapping("/modifierroom")
    public String modifierRoomAction(Model model,
                                     @RequestParam(name = "id") Integer id,
                                     @RequestParam(name = "roomNum") String roomNum,
                                     @RequestParam(name = "price") Double price,
                                     @RequestParam(name = "description") String description,
                                     @RequestParam(name = "available", defaultValue = "false") boolean available,
                                     @RequestParam(name = "roomType") String roomType){

        Room room = roomService.getRoomById(id);
        if (room != null) {
            // Mettre à jour les champs modifiables avec les nouvelles valeurs
            room.setRoomNumber(roomNum);
            room.setPricePerNight(price);
            room.setDescription(description);
            room.setAvailable(available);

            room.setRoomType(roomType);


            roomService.updateRoom(room);
            return "redirect:/listroom";
        } else {
            return "error";
        }
    }


}
