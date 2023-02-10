import React, { FC, InputHTMLAttributes } from 'react';
import Box from '@mui/material/Box';
import { getServices } from '../api/services';

import {Avatar, Button, Input, InputLabel} from '@mui/material';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';

const style = {
  position: 'absolute' as 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

interface ModalInputProps extends InputHTMLAttributes<HTMLInputElement> {
    label: string;
}
export const ServiceModal: FC<ModalInputProps> =  () => {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <div>
        <Button onClick={handleOpen}>
      <Avatar alt="edit" src="../../../static/imgs/edit.png" ></Avatar>
      </Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
        <div>
            <div className="col-sm-4">
            <InputLabel children="Name"></InputLabel>
            <Input name="serviceName" placeholder='service 1'/>
            </div>
            <div className="col-sm-4">
            <InputLabel children="Url"></InputLabel>
            <Input name="serviceUrl" placeholder='http://localhost/service'/>
            </div>
            <div className="col-sm-12"><Button onClick={getServices}>Save</Button></div>
        </div>
            
        </Box>
      </Modal>
    </div>
  );
}