package ouisncf;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import packingFactory.PackingProcess;


@Controller
public class TestController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("")
	public String submitWeights(@RequestParam ArrayList<Integer> weights, Model model){
		PackingProcess process = new PackingProcess(weights);
        model.addAttribute("weights", process.getWeights());
        
        String packing = process.processPacking();
        model.addAttribute("packing", packing);
		return "list";
	}
}
