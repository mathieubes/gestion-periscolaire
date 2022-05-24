import React from 'react';
import './card.scss';

interface IProps {
  title: string;
  children: React.ReactNode;
}

export const Card: React.FC<IProps> = ({ title, children }) => {
  return (
    <div className="card">
      <h2>{title}</h2>
      <div className="card__content">{children}</div>
    </div>
  );
};
