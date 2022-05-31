import React from 'react';
import './card.scss';

interface IProps {
  className?: string;
  title?: string;
  center?: boolean;
  onClick?: any;
  children: React.ReactNode;
}

export const Card: React.FC<IProps> = ({
  className,
  title,
  center,
  children,
  onClick,
}) => {
  return (
    <div
      className={`card ${center ? 'card--centered' : ''} ${className || ''}`}
      onClick={() => onClick()}
    >
      {title && <h2>{title}</h2>}
      <div className="card__content">{children}</div>
    </div>
  );
};
