import { Component, OnInit } from '@angular/core';
import * as jquery from 'jquery';
import { PrivacyModalComponent } from './privacy-modal/privacy-modal.component';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-strona-glowna',
  templateUrl: './strona-glowna.component.html',
  styleUrls: ['./strona-glowna.component.css']
})
export class StronaGlownaComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
    this.openDialog();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(PrivacyModalComponent, {
      width: '600px',
    });
  }

}
