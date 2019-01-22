import { Component, OnInit } from '@angular/core';
declare let $: any;

@Component({
  selector: 'app-strona-glowna',
  templateUrl: './strona-glowna.component.html',
  styleUrls: ['./strona-glowna.component.css']
})
export class StronaGlownaComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    $(window).on('load', function() {
      $('#exampleModalCenter').modal('show');
    });
  }

}
